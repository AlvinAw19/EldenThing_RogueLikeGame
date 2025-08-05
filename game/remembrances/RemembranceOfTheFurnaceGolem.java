package game.remembrances;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.attributes.Ability;
import game.statusEffects.HealingOverTimeEffect;
import game.weapons.FurnaceEngine;

/**
 * Class representing the Remembrance of the Furnace Golem.
 * This item can be traded and provides specific benefits to the actor who trades it.
 *
 * Author: Au Jenq
 */
public class RemembranceOfTheFurnaceGolem extends Remembrances {
    /**
     * Constructor for RemembranceOfTheFurnaceGolem.
     */
    public RemembranceOfTheFurnaceGolem() {
        super("Remembrance of the Furnace Golem");
    }

    /**
     * Applies the trade effect to the given actor.
     * Adds a HealingOverTimeEffect status effect to the actor, removes the item from the actor's inventory,
     * and adds a Furnace Engine item to the actor's inventory.
     *
     * @param actor the actor to apply the trade effect to
     */
    @Override
    public void applyTradeEffect(Actor actor) {
        actor.addStatusEffect(new HealingOverTimeEffect());
        actor.addItemToInventory(new FurnaceEngine());
    }

    /**
     * Returns the item to be traded.
     *
     * @return the Furnace Engine item
     */
    @Override
    public Item getTradeItem() {
        return new FurnaceEngine();
    }
}

