package game.weapons.weaponart;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class representing the Quickstep weapon art that allows the attacker to move to a random, valid
 * location adjacent to the target at no mana cost.
 * Created by:
 * @author Asyraaf Rahman
 */
public class Quickstep implements WeaponArt {
    private static final int DEFAULT_MANA_COST = 0;

    /**
     * Executes the Quickstep ability.
     * The attacker moves to a random adjacent location if there are valid exits.
     * If no valid exits exist, the attacker cannot move.
     *
     * @param attacker The actor using the weapon art.
     * @param target The actor being attacked (not directly affected by Quickstep).
     * @param map The game map where the attack takes place.
     * @return A string describing the result of using Quickstep.
     */
    @Override
    public String use(Actor attacker, Actor target, GameMap map) {
        Location currentLocation = map.locationOf(attacker);
        List<Exit> adjacentExits = new ArrayList<>(currentLocation.getExits());

        Random rand = new Random();

        for (int i = 0; i < adjacentExits.size(); i++) {
            Exit exit = adjacentExits.get(rand.nextInt(adjacentExits.size()));
            Location newLocation = exit.getDestination();

            if (!newLocation.containsAnActor()) {
                map.moveActor(attacker, newLocation);
                return attacker + " escapes to " + newLocation;
            }
        }
        return attacker + " cannot escape";
    }

    /**
     * Returns the empty mana cost for using Quickstep.
     * @return The mana cost of Quickstep, which is 0.
     */
    @Override
    public int manaCost() {
        return DEFAULT_MANA_COST;
    }

    @Override
    public String toString() {
        return "Quickstep";
    }
}