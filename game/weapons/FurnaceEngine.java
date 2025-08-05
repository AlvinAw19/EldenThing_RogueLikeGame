package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.BurnSurroundings;
import game.behaviours.Explosion;
import game.behaviours.StompBehaviour;

import java.util.Random;

/**
 * Class representing the Furnace Engine weapon.
 * This weapon can be used by an actor to perform a stomp attack.
 * Author: Au Jenq
 */
public class FurnaceEngine extends WeaponItem{ ;
    private static final int HIT_RATE = 5;
    private static final int DAMAGE = 100;

    /**
     * Constructor for FurnaceEngine.
     * Initializes the weapon with its name, display character, damage, verb, hit rate, and other attributes.
     */
    public FurnaceEngine() {
        super("Furnace Engine", 'E', DAMAGE, "stomps", HIT_RATE, 0, null, 999);
    }

    /**
     * Performs the attack action.
     * Executes the attack action and has a chance to trigger an explosion and burn surroundings.
     *
     * @param attacker the actor performing the attack
     * @param target the actor being attacked
     * @param map the current game map
     * @return a string description of the attack action
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map){
        StompBehaviour stompBehaviour = new StompBehaviour(target);
        return stompBehaviour.execute(attacker, map);
    }
}
