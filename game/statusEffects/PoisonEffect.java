package game.statusEffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

/**
 * The PoisonStatus class can manage stacking poison effects,
 * ensuring that each new poison effect adds its own duration and damage.
 * @author Au Jenq & Aw Shen Yang
 */
public class PoisonEffect extends StatusEffect {
    private final int POISON_DAMAGE;
    private int durationRemaining;
    private Actor actor;

    /**
     * Constructor for the poison effect.
     *
     * @param damage Amount of damage to be dealt per tick.
     * @param duration Number of turns the poison effect lasts.
     */
    public PoisonEffect(int damage, int duration, Actor actor) {
        super("Poison");
        this.POISON_DAMAGE = damage;
        this.durationRemaining = duration;
        this.actor = actor;
    }

    /**
     * Apply the poison effect each tick.
     *
     * @param location The location of the affected actor.
     * @param target The actor affected by the poison.
     */
    @Override
    public void tick(Location location, Actor target) {
        Display display = new Display();
        if (durationRemaining > 0 && target.isConscious()) {
            target.hurt(POISON_DAMAGE);
            durationRemaining--;
            display.println("Turns left: " + durationRemaining);
            display.println(target + " suffers " + POISON_DAMAGE + " poison damage!");
            if (!(target.isConscious())){
                if (actor != null){
                    display.println(target.unconscious(actor, location.map()));
                }
                else {
                    display.println(target.unconscious(location.map()));
                }
            }
        }
        // Remove the poison effect when the duration ends
        if (durationRemaining <= 0 && target.isConscious()) {
            target.removeStatusEffect(this);
            display.println(target + " is no longer poisoned.");
        }
    }
}
