package game.statusEffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents a buff given by consuming a Scarab, which temporarily increases
 * an actor's maximum hit points and mana points.
 * @author Somkiet Phromsuwan
 */
public class ScarabBuffEffect extends StatusEffect {
    private final int MAX_HIT_POINTS_GAINED = 30;
    private final int MAX_MANA_GAINED = 50;
    private int BUFF_DURATION_LEFT = 10;
    private int initialMaxHitPoints;
    private int initialMaxManaPoints;

    /**
     * Constructor for ScarabBuffEffect.
     * @param initialMaxHitPoints The initial maximum hit points of the actor.
     * @param initialMaxManaPoints The initial maximum mana points of the actor.
     */
    public ScarabBuffEffect(int initialMaxHitPoints, int initialMaxManaPoints){
        super("Scarab Buff");
        this.initialMaxHitPoints = initialMaxHitPoints;
        this.initialMaxManaPoints = initialMaxManaPoints;
    }

    /**
     * Applies the buff on the first turn and decreases the buff duration each tick.
     * Once the buff duration ends, it restores the actor's maximum hit points and mana to their initial values.
     * @param location The location of the actor in the game world.
     * @param actor The actor affected by the buff.
     */
    @Override
    public void tick(Location location, Actor actor){
        if (BUFF_DURATION_LEFT == 10 && actor.isConscious()){
            actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, MAX_HIT_POINTS_GAINED);
            actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, MAX_MANA_GAINED);
        }

        if (BUFF_DURATION_LEFT <= 0 && actor.isConscious()){
            int currentHitPoints = actor.getAttribute(BaseActorAttributes.HEALTH);
            actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, initialMaxHitPoints);
            if (currentHitPoints < initialMaxHitPoints){
                actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, currentHitPoints);
            }

            int currentMana = actor.getAttribute(BaseActorAttributes.MANA);
            actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.UPDATE, initialMaxManaPoints);
            if (currentMana < initialMaxManaPoints){
                actor.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.UPDATE, currentMana);
            }
            actor.removeStatusEffect(this);
        }

        BUFF_DURATION_LEFT--;

    }
}
