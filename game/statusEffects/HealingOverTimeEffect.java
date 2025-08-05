package game.statusEffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Status effect representing the healing power.
 * It heals the actor for 30 hit points per turn for 5 turns.
 * @author Somkiet Phromsuwan
 */
public class HealingOverTimeEffect extends StatusEffect {
    private final int HIT_POINTS_GAINED_PER_TURN = 30;
    private int durationLeft = 5;

    /**
     * Constructor for Crimson Tear Effect.
     */
    public HealingOverTimeEffect(){
        super("Healing Over Time Effect");
    }

    /**
     * Called every turn to apply the healing effect.
     * Heals the actor and prints a message to indicate the healing. After 5 turns, the effect is removed.
     * @param location The current location of the actor.
     * @param actor    The actor affected by the Crimson Tear.
     */
    @Override
    public void tick(Location location, Actor actor){
        Display display = new Display();
        if (durationLeft > 0 && actor.isConscious()){
            actor.heal(HIT_POINTS_GAINED_PER_TURN);
            display.println(actor + " is healed for " + HIT_POINTS_GAINED_PER_TURN + " hit points.");
        }

        if (durationLeft <= 0 && actor.isConscious()){
            actor.removeStatusEffect(this);
        }

        durationLeft--;
    }
}
