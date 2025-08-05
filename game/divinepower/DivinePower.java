package game.divinepower;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attributes.Ability;

/**
 * Interface for defining Divine Power abilities for an actor.
 * Each Divine Power should have a special attack and transition to the next power.
 * @author Aw Shen Yang
 */
public interface DivinePower {
    /**
     * Perform a special attack using this Divine Power.
     *
     * @param enemy  The actor performing the attack.
     * @param target The target actor receiving the attack.
     * @param map    The current game map.
     */
    void performSpecialAttack(Actor enemy, Actor target, GameMap map);

    /**
     * Get the next Divine Power in the sequence.
     *
     * @return The next Ability in the Divine Power sequence.
     */
    Ability getNextPower();
}
