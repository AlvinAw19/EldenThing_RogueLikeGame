package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.statusEffects.HealingOverTimeEffect;

/**
 * A consumable item representing a Crimson Tear
 * When consumed, it applies the CrimsonTearEffect to the player.
 * @author Somkiet Phromsuwan
 */
public class CrimsonTear extends ConsumableItem{

    /**
     * Constructor to create a new Crimson Tear item.
     */
    public CrimsonTear(){
        super("Crimson Tear", '*', 10);
    }

    /**
     * Defines the behavior of consuming the Crimson Tear.
     * When consumed, the item applies the CrimsonTearEffect to the player and removes itself from the player's inventory.
     * @param player The actor consuming the item.
     * @param item   The consumable item (in this case, the Crimson Tear).
     * @param map    The map on which the action takes place.
     * @return A string showing the result of the consumption.
     */
    @Override
    public String consume(Actor player, Consumable item, GameMap map) {
        player.addStatusEffect(new HealingOverTimeEffect());
        player.removeItemFromInventory(this);
        return this + " consumed by " + player;
    }
}
