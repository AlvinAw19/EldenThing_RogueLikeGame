package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.

 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 *
 */
public class FollowBehaviour implements Behaviour {
    private final Actor TARGET;

    /**
     * Non default Constructor.
     *
     * @param player The actor to be followed
     */
    public FollowBehaviour(Actor player) {
        this.TARGET = player;
    }


    /**
     * Moves the actor to the target actor.
     * Checks the current location of the actor and the target.
     * Move the actor to a location that brings it closer to the target.
     *
     * @param actor The actor performing the behaviour
     * @param map The game map containing the actor and the target
     * @return An action to move the actor towards the target or null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Display display = new Display();

        if (!map.contains(TARGET) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(TARGET);

        int currentDistance = distance(here, there);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, there);
                if (newDistance < currentDistance) {
                    display.println(actor + " is following " + TARGET);
                    return new MoveActorAction(destination, exit.getName());
                }
            }
        }

        return null;
    }

    /**
     * Calculates the distance between two locations.
     * @param a location one
     * @param b location two
     * @return The distance between the two locations
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());

    }
}
