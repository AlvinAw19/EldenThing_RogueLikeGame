package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * The Traverse class represents an action where an actor traverses to a specified location.
 * It extends the abstract Action class, adhering to the Dependency Inversion Principle (DIP).
 * @author Au Jenq
 */

public class Traverse extends Action {
    private Location traverseDestination;

    /**
     * Constructor for the Traverse class.
     *
     * @param traverseDestination The destination location to which the actor will traverse.
     */

    public Traverse(Location traverseDestination) {
        this.traverseDestination = traverseDestination;
    }

    /**
     * Executes the traverse action, moving the actor to the specified destination.
     *
     * @param actor The actor performing the traverse action.
     * @param map The game map on which the action is performed.
     * @return A string describing the result of the action.
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        if (traverseDestination.containsAnActor()){
            return "The gate is blocked by another actor";
        }
        map.moveActor(actor, traverseDestination);
        return "You have traversed from " + map.toString() + " to " + traverseDestination.map().toString();
    }

    /**
     * Provides a description of the traverse action for the menu.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action for the menu.
     */

    @Override
    public String menuDescription(Actor actor){
        return actor + " enters the gate to " + traverseDestination.map().toString();
    };
}
