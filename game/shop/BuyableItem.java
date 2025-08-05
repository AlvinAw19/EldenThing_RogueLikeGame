package game.shop;

import edu.monash.fit2099.engine.items.Item;

/**
 * An abstract class representing buyable items.
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public abstract class BuyableItem extends Item {

    /**
     * Constructor
     *
     * @param name The name of the buyable item
     * @param displayChar The character that represents the buyable item on the map.
     */
    public BuyableItem(String name, char displayChar) {
        super(name, displayChar, true);
    }

    /**
     * Abstract method to get the price of the item.
     * @return The price of the item.
     */
    public abstract int getPrice();
}
