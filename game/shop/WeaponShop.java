package game.shop;

import game.shop.Shop;
import game.weapons.WeaponItem;

/**
 * A class representing a Weapon shop where actors can buy weapons.
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public class WeaponShop extends Shop {

    /**
     * Constructor to initialize the WeaponShop with a display character and name.
     *
     * @param displayChar the character representing the shop on the map.
     * @param name the name of the shop.
     */
    public WeaponShop(char displayChar, String name) {
        super(displayChar, name);
    }

    /**
     * Adds a Weapon item to the shop's inventory.
     * @param weapon The weapon to be added
     */
    public void addWeapon(WeaponItem weapon) {
        super.addItem(weapon);
    }
}
