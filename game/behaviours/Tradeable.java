package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * Abstract class representing a tradeable item.
 * Classes extending this class can be traded with actors.
 * Author: Au Jenq
 */
public abstract class Tradeable extends Item {
    /**
     * Constructor for Tradeable.
     * Initializes the item with the specified name.
     *
     * @param name the name of the tradeable item
     */
    public Tradeable(String name) {
        super(name, '*', true);
    }

    /**
     * Applies the trade effect to the given actor.
     *
     * @param actor the actor to apply the trade effect to
     */
    public abstract void applyTradeEffect(Actor actor);

    /**
     * Returns the item to be traded.
     *
     * @return the item to be traded
     */
    public abstract Item getTradeItem();
}
