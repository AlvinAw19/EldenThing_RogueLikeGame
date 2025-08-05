package game.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.shop.BuyableItem;

/**
 * An abstract class representing consumable items
 *
 * @author Somkiet Phromsuwan
 */
public abstract class ConsumableItem extends BuyableItem implements Consumable{
    private final int price;

    /**
     * Constructor
     *
     * @param name The name of the consumable item
     * @param displayChar The character that represents the item on the map.
     * @param price The price of the consumable item
     */
    public ConsumableItem(String name, char displayChar, int price){
        super(name, displayChar);
        this.price = price;
    }

    /**
     * Defines the behaviour of when the item is consumed
     * To be overrided by subclasses to have their own behaviour
     * @param player The actor consuming the item
     * @param item The consumable to be consumed
     * @param map The map on which the action takes place
     * @return A string showing the result of the consumption
     */
    @Override
    public abstract String consume(Actor player, Consumable item, GameMap map);


    /**
     * Returns the list of actions that can be performed on this consumable item by the owner.
     * In this case, it adds a ConsumeAction allowing the owner to consume the item.
     * @param owner The actor who owns the item and can perform actions on it.
     * @return An ActionList containing the consume action for the owner.
     */
    @Override
    public ActionList allowableActions(Actor owner){
        ActionList actions = new ActionList(new ConsumeAction(owner,this));
        return actions;
    }

    @Override
    public int getPrice(){
        return price;
    }

}
