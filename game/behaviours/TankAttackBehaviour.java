package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.attributes.Ability;

/**
 * A behaviour that allows an actor to perform a tank attack.
 * This attack damages an adjacent enemy.
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public class TankAttackBehaviour extends Action implements Behaviour {

    private final Actor ENEMY;
    private final int HIT_DAMAGE = 10;

    /**
     * Constructor to create a TankAttackBehaviour targeting a specific enemy.
     *
     * @param enemy The enemy actor targeted by the tank attack.
     */
    public TankAttackBehaviour(Actor enemy) {
        this.ENEMY = enemy;
    }

    /**
     * Executes the tank attack, dealing damage to the first adjacent enemy
     * that is not immune.
     *
     * @param actor The actor performing the attack.
     * @param map The map where the actor is located.
     * @return A string describing the result of the action, including damage dealt.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String ret = "";

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location coordinate = exit.getDestination();
            if (coordinate.containsAnActor()) {
                Actor enemy = coordinate.getActor();
                if (!enemy.hasCapability(Ability.IMMUNE)) {
                    enemy.hurt(HIT_DAMAGE);
                    ret += actor + " attacks " + enemy + " for " + HIT_DAMAGE + " damage";
                    if (!enemy.isConscious()) {
                        ret += '\n' + enemy.unconscious(actor, map);
                    }
                    break;
                }
            }
        }
        return ret;
    }


    @Override
    public String menuDescription(Actor actor) {
        return "";
    }

    /**
     * Determines if this action should be performed based on the location of the actor
     * and the presence of the targeted enemy.
     *
     * @param actor The actor performing the action.
     * @param map The map where the actor is located.
     * @return This action if the targeted enemy is adjacent; null otherwise.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination() == map.locationOf(ENEMY)) {
                return this;
            }
        }
        return null;
    }
}
