package game.shop;

import game.consumables.ConsumableItem;
import game.shop.Shop;

/**
 * A class representing a consumable shop where actors can buy consumable items.
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public class ConsumableShop extends Shop {

    /**
     * Constructor to initialize the ConsumableShop with a display character and name.
     *
     * @param displayChar the character representing the shop on the map.
     * @param name the name of the shop.
     */
    public ConsumableShop(char displayChar, String name) {
        super(displayChar, name);
    }

    /**
     * Adds a consumable item to the shop's inventory.
     *
     * @param consumable The consumable item to be added.
     */
    public void addConsumable(ConsumableItem consumable) {
        super.addItem(consumable);
    }
}
