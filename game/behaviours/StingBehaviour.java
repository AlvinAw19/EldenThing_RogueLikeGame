package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.*;
import game.statusEffects.PoisonEffect;
import game.attributes.Ability;
import java.util.Random;

/**
 * Behaviour that allows an enemy to sting another actor, usually the player.
 * The sting attack has a chance to deal damage and potentially inflict poison.
 * If the target is resistant to poison, the poison effect will not be applied.
 *
 * Created by: Aw Shen Yang
 */
public class StingBehaviour extends Action implements Behaviour {

    private final Actor TARGET;
    private final int STING_CHANCE = 25;
    private final int STING_DAMAGE = 20;
    private final int POISON_CHANCE = 30;
    private final int POISON_DAMAGE = 10;
    private final int POISON_DURATION = 2;

    /**
     * Constructor for StingBehaviour.
     *
     * @param player The actor that will be targeted by the sting attack.
     */
    public StingBehaviour(Actor player) {
        this.TARGET = player;
    }

    /**
     * Executes the sting attack.
     * Deals damage to the target and may apply a poison effect if conditions are met.
     *
     * @param enemy The actor performing the sting attack.
     * @param map The game map containing the actor and the target.
     * @return A string describing the outcome of the sting attack.
     */
    @Override
    public String execute(Actor enemy, GameMap map) {
        String ret = "";

        Random random = new Random();
        if (random.nextInt(100) < STING_CHANCE) {
            this.TARGET.hurt(STING_DAMAGE);
            ret += enemy + " successfully stings " + TARGET + " causing " + STING_DAMAGE + " damage.";

            // Check for poison application
            if (random.nextInt(100) < this.POISON_CHANCE) {
                if (!TARGET.hasCapability(Ability.POISON_RESISTANT)) {
                    TARGET.addStatusEffect(new PoisonEffect(POISON_DAMAGE, POISON_DURATION, enemy));
                    ret += " " + TARGET + " is poisoned!";
                } else {
                    ret += " " + TARGET + " is resistant to poison!";
                }
                if (!(TARGET.isConscious())){
                    ret += '\n'+ TARGET.unconscious(enemy, map);
                }
            }
        } else {
            ret += enemy + " misses " + TARGET;
        }

        return ret;
    }

    /**
     * A method to provide a menu description for the action.
     * Not used in this class.
     *
     * @param actor The actor performing the action.
     * @return An empty string as no menu description is provided.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "";
    }

    /**
     * Determines if the sting action should be performed.
     * The sting can only occur if the target is adjacent to the actor.
     *
     * @param actor The actor performing the sting action.
     * @param map The game map containing the actor and the target.
     * @return This action if the target is adjacent to the actor, otherwise null.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination() == map.locationOf(TARGET)) {
                return this;
            }
        }
        return null;
    }
}
