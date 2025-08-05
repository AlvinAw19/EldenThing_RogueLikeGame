package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A consumable item that represents a Flask of Rejuvenation.
 * @author Somkiet Phromsuwan
 */
public class FlaskofRejuvenation extends ConsumableItem {
    private final int MANA_POINTS_GAINED = 100;
    private int charges = 3;

    /**
     * Default Constructor for a Flask of Rejuvenation.
     */
    public FlaskofRejuvenation(){
        super("Flask of Rejuvenation", 'o', 5);
    }

    /**
     * Consumes the Flask of Rejuvenation, resulting in the actor to restore mana
     * @param player The actor consuming the item
     * @param item The consumable to be consumed
     * @param map The map on which the action takes place
     * @return A string describing the result of the consumption
     */
    @Override
    public String consume(Actor player, Consumable item, GameMap map) {
        if (this.charges > 0) {
            player.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, MANA_POINTS_GAINED);
            this.charges -= 1;
            return item + " consumed by " + player + ". " + player + " feels rejuvenated.";
        }
        return item + " is empty.";
    }
}
