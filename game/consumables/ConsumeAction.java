package game.consumables;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Scarab;

/**
 * Action that represents an actor consuming a {@link ConsumableItem}.
 * This action performs the consumption behavior defined by the {@link ConsumableItem}.
 * @author Somkiet Phromsuwan
 */
public class ConsumeAction extends Action {

    private Consumable consumable;

    private Actor player;

    /**
     * Non default Constructor for a consume action.
     *
     * @param player The actor consuming the item
     * @param consumable The consumable item being consumed
     */
    public ConsumeAction(Actor player, Consumable consumable){
        this.player = player;
        this.consumable = consumable;
    }

    /**
     * Executes the consume action for the consumable item by the actor
     * @param player The actor performing the action
     * @param map The map on which the action takes place
     * @return A string showing the result of the consumption
     */
    @Override
    public String execute(Actor player, GameMap map){
        String result = consumable.consume(player, consumable, map);
        return result;
    }


    /**
     * Provides a description of the action in the menu.
     * @param actor The actor performing the action
     * @return A string describing the action for the menu
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " consumes " +  consumable;
    }


}
