package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.attributes.Ability;
import game.grounds.Fire;

/**
 * Class representing an action that sets the surroundings on fire.
 * The action sets the surroundings on fire by adding a Fire item to the surrounding locations.
 * The action is used by the Furnace Golem to set the surroundings on fire when it is defeated.
 * Author: Au Jenq
 */
public class BurnSurroundings extends Action {

    /**
     * Executes the action by setting the surroundings on fire.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()){
            Location coordinates = exit.getDestination();
            Ground ground = coordinates.getGround();
            if (!(ground.hasCapability(Ability.FIRE_RESISTANT))){
                coordinates.addItem(new Fire(actor));
            }
        }
        return "The surroundings are set on fire!";
    }

    /**
     * Provides a description for the menu when this action is selected.
     * @param actor The actor performing the action.
     * @return An empty string, as this action does not appear directly in a menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "";
    }
}

