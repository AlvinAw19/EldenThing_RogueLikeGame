package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.Traverse;

import java.util.ArrayList;
import java.util.List;

/**
 * The Gate class represents a gate that connects different locations in the game.
 * It extends the Ground class and provides actions for traversing between these locations.
 * Now supports multiple travel locations via a list.
 * @author Au Jenq, Aw Shen Yang
 */
public class Gate extends Ground {
    private List<Location> connectedLocations;  // List to store multiple gate locations.

    /**
     * Constructor for the Gate class.
     * Initializes the gate with a list of connected locations.
     *
     * @param connectedLocations The list of locations this gate connects to.
     */
    public Gate(List<Location> connectedLocations) {
        super('H', "Gate to other map");
        this.connectedLocations = connectedLocations;
    }

    /**
     * Provides the list of allowable actions for an actor at a given location.
     * Allows the player to travel to any connected location in the list.
     *
     * @param actor The actor performing the action.
     * @param location The current location of the actor.
     * @param direction The direction of the actor's movement.
     * @return An ActionList containing the allowable actions.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        // Iterate over the list of connected locations and add Traverse actions.
        for (Location connectedLocation : connectedLocations) {
            // Only add traversal if the actor is on a different map
            if (connectedLocation.map() != location.map()) {
                actions.add(new Traverse(connectedLocation));
            }
        }

        return actions;
    }
}
