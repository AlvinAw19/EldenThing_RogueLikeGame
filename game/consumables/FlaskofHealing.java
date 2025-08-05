package game.consumables;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A consumable item that represents a Flask of Healing.
 * @author Somkiet PHromsuwan
 */
public class FlaskofHealing extends ConsumableItem {

    private final int HIT_POINTS_GAINED = 150;
    private int charges = 5;

    /**
     * Default constructor for a Flask of Healing.
     */
    public FlaskofHealing(){
        super("Flask of Healing", 'u', 5);
    }


    /**
     * Consumes the Flask of Healing, healing the actor that consumes it and using up a charge
     * @param player The actor consuming the item
     * @param item The consumable to be consumed
     * @param map The map on which the action takes place
     * @return A string describing the result of the consumption
     */
    @Override
    public String consume(Actor player, Consumable item, GameMap map) {
        if (this.charges > 0) {
            player.heal(HIT_POINTS_GAINED);
            this.charges -= 1;
            return item + " consumed by " + player + ". " + player + " is healed.";
        }
        return item + " is empty.";
    }
}
