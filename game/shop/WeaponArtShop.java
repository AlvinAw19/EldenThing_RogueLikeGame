package game.shop;

import game.shop.Shop;
import game.weapons.WeaponItem;

/**
 * A class representing a WeaponArt shop where actors can buy weapons with weapon art
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public class WeaponArtShop extends Shop {

    /**
     * Constructor to initialize the WeaponArtShop with a display character and name.
     *
     * @param displayChar the character representing the shop on the map.
     * @param name        the name of the shop.
     */
    public WeaponArtShop(char displayChar, String name) {
        super(displayChar, name);
    }

    /**
     * Adds a Weapon item with weapon art to the shop's inventory.
     *
     * @param weapon The weapon to be added
     */
    public void addWeaponWithWeaponArt(WeaponItem weapon) {
        if (weapon.hasWeaponArt()) {
            super.addItem(weapon);
        }
    }
}
