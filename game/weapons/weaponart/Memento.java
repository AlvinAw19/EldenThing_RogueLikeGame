package game.weapons.weaponart;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.snapshot.Snapshot;

import java.util.List;
import java.util.Random;

/**
 * The Memento class implements the WeaponArt interface, allowing an Actor
 * to remember and restore their past states during an attack.
 *
 * The weapon art holds a list of mementos, allowing one or multiple snapshots to be used.
 *
 * When used, Memento has a 50% chance to either save the current
 * attributes (health, mana, and strength) of the attacking Actor or
 * restore them to the last saved state from the snapshots. Either one happens at a cost of 5 HP.
 *
 * @author Asyraaf Rahman
 */
public class Memento implements WeaponArt
{
    private final List<Snapshot> snapshots;
    private static final int HEALTH_COST = 5;
    private static final int MANA_COST = 0;

    public Memento(List<Snapshot> snapshots) {
        this.snapshots = snapshots;
    }

    @Override
    public String use(Actor attacker, Actor target, GameMap map) {
        if (!hasEnoughHealth(attacker)) {
            return "";
        }

        String result;
        Random random = new Random();

        if (random.nextBoolean()) {
            saveState(attacker);
            result = attacker + " remembers their past state";
        } else {
            restoreState(attacker);
            result = attacker + " restores their past state";
        }

        decreaseHealth(attacker);

        return result;
    }

    /**
     * Checks if the attacker has enough health to use the ability.
     *
     * @param attacker the actor attempting to use the ability
     * @return true if the attacker has sufficient health; otherwise, false
     */
    private boolean hasEnoughHealth(Actor attacker) {
        return attacker.getAttribute(BaseActorAttributes.HEALTH) > HEALTH_COST;
    }

    /**
     * Saves the current state of the attacker using snapshots.
     *
     * @param attacker the actor whose state will be saved
     */
    private void saveState(Actor attacker) {
        for (Snapshot snapshot : snapshots) {
            snapshot.save(attacker);
        }
    }

    /**
     * Restores the attacker's state using snapshots.
     *
     * @param attacker the actor whose state will be restored
     */
    private void restoreState(Actor attacker) {
        for (Snapshot snapshot : snapshots) {
            snapshot.restore(attacker);
        }
    }

    /**
     * Decreases the attacker's health by a predefined cost.
     *
     * @param attacker the actor whose health will be decreased
     */
    private void decreaseHealth(Actor attacker) {
        attacker.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.DECREASE, HEALTH_COST);
    }

    @Override
    public int manaCost() {
        return MANA_COST;
    }

    @Override
    public String toString() {
        return "Memento";
    }
}
