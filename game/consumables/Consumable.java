package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface that defines consumable items in the game.
 * Classes that implement this interface should define the specific behavior
 * when the item is consumed by an actor.
 * Created by: Somkiet Phromsuwan
 */
public interface Consumable {

    /**
     * Defines the behaviour of when the item is consumed
     * To be overrided by subclasses to have their own behaviour
     * @param player The actor consuming the item
     * @param item The consumable to be consumed
     * @param map The map on which the action takes place
     * @return A string showing the result of the consumption
     */
    String consume(Actor player, Consumable item, GameMap map);
}
