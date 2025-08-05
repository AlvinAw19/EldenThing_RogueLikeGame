package game.shop;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class representing a shop where actors can buy items.
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public abstract class Shop extends Ground {
    private List<BuyableItem> itemsForSale;

    /**
     * Constructor to initialize the shop with a display character and name.
     *
     * @param displayChar the character representing the shop on the map.
     * @param name the name of the shop.
     */
    public Shop(char displayChar, String name) {
        super(displayChar, name);
        this.itemsForSale = new ArrayList<>();
    }

    /**
     * Adds an item to the shop's inventory.
     *
     * @param item The item to be added.
     */
    public void addItem(BuyableItem item) {
        itemsForSale.add(item);
    }


    /**
     * Returns the actions available to the actor in this shop, including buying items.
     *
     * @param actor The actor interacting with the shop.
     * @param location The current location of the actor.
     * @param direction The direction from the actor to the shop.
     * @return A list of actions available to the actor.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (location.getActor() == actor) {
            for (BuyableItem item : itemsForSale) {
                int price = item.getPrice();
                actions.add(new BuyAction(item, price, this));
            }
        }
        return actions;
    }
}
