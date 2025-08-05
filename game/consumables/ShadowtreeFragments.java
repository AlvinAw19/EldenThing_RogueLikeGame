package game.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attributes.NewBaseActorAttributes;

/**
 * A consumable item that represents ShadowTree Fragments
 * @author Somkiet Phromsuwan
 */
public class ShadowtreeFragments extends ConsumableItem {
    private final int MAX_HIT_POINTS_GAINED = 50;
    private final int MAX_MANA_POINTS_GAINED = 25;
    private final int MAX_STRENGTH_POINTS_GAINED = 5;

    /**
     * Default constructor for ShadowTree Fragments.
     */
    public ShadowtreeFragments(){
        super("ShadowTree Fragments", 'e',10);
    }

    /**
     * Consumes the ShadowTree Fragments, resulting in the actor to gain max attributes
     * @param player The actor consuming the item
     * @param item The consumable to be consumed
     * @param map The map on which the action takes place
     * @return A string describing the result of the consumption
     */
    @Override
    public String consume(Actor player, Consumable item, GameMap map) {
        player.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, MAX_HIT_POINTS_GAINED);
        player.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, MAX_MANA_POINTS_GAINED);
        player.modifyAttributeMaximum(NewBaseActorAttributes.STRENGTH, ActorAttributeOperations.INCREASE, MAX_STRENGTH_POINTS_GAINED);
        player.removeItemFromInventory(this);
        return item + " consumed by " + player + ". " + player + " feels stronger.";
    }
}
    