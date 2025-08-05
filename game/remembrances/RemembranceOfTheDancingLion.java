package game.remembrances;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.attributes.Ability;
import game.weapons.DivineBeastHead;

/**
 * Class representing the Remembrance of the Dancing Lion.
 * This item can be traded and provides specific benefits to the actor who trades it.
 * Author: Au Jenq
 */
public class RemembranceOfTheDancingLion extends Remembrances {
    private final int MAX_HIT_POINTS_GAINED = 50;
    private final int MAX_MANA_POINTS_GAINED = 100;

    /**
     * Constructor for RemembranceOfTheDancingLion.
     */
    public RemembranceOfTheDancingLion() {
        super("Remembrance of the Dancing Lion");
    }

    /**
     * Applies the trade effect to the given actor.
     * Increases the actor's maximum hit points and mana points, removes the item from the actor's inventory,
     * and adds a Divine Beast Head item to the actor's inventory.
     *
     * @param actor the actor to apply the trade effect to
     */
    @Override
    public void applyTradeEffect(Actor actor) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, MAX_HIT_POINTS_GAINED);
        actor.modifyAttributeMaximum(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, MAX_MANA_POINTS_GAINED);
        actor.addItemToInventory(new DivineBeastHead());
    }

    /**
     * Returns the item to be traded.
     *
     * @return the Divine Beast Head item
     */
    @Override
    public Item getTradeItem() {
        return new DivineBeastHead();
    }
}

