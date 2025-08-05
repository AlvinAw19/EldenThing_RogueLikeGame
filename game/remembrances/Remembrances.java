package game.remembrances;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.behaviours.Tradeable;

/**
 * Abstract class representing a Remembrance item.
 * Remembrances are special items that can be traded and provide specific benefits to the actor who trades them.
 * This class implements the Tradeable interface.
 *
 * Author: Au Jenq
 */
public abstract class Remembrances extends Tradeable {
    /**
     * Constructor for Remembrances.
     * Initializes the item with the specified name.
     *
     * @param name the name of the remembrance item
     */
    public Remembrances(String name) {
        super(name);
    }

    /**
     * Applies the trade effect to the given actor.
     * This method must be implemented by subclasses to define the specific trade effect.
     *
     * @param actor the actor to apply the trade effect to
     */
    @Override
    public abstract void applyTradeEffect(Actor actor);
}
