package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attributes.Ability;
import game.behaviours.DivinePowerBiteBehaviour;

/**
 * Class representing the Divine Beast Head weapon.
 * This weapon can be used by an actor to perform a powerful bite attack.
 *
 * Author: Au Jenq
 */
public class DivineBeastHead extends WeaponItem {
    /**
     * Constructor for DivineBeastHead.
     * Initializes the weapon with its name, display character, damage, verb, hit rate, and other attributes.
     */
    public DivineBeastHead() {
        super("Divine Beast Head", '$', 150, "bites", 30, 0, null, 999);
    }

    /**
     * Performs the attack action.
     * Adds divine power capabilities to the attacker and executes the DivinePowerBiteBehaviour.
     *
     * @param attacker the actor performing the attack
     * @param target the actor being attacked
     * @param map the current game map
     * @return a string description of the attack action
     */
    public String attack(Actor attacker, Actor target, GameMap map) {
        DivinePowerBiteBehaviour divinePowerBiteBehaviour = new DivinePowerBiteBehaviour(target);
        return divinePowerBiteBehaviour.execute(attacker, map);
    }
}

