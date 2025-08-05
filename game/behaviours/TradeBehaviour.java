package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class representing the trade behavior.
 * This behavior allows an actor to trade a tradeable item.
 * Author: Au Jenq
 */
public class TradeBehaviour extends Action {
    private final Tradeable tradeable;
    private final Item tradeItem;

    /**
     * Constructor for TradeBehaviour.
     * Initializes the trade behavior with the specified tradeable item.
     * The tradeable item is the item to be traded.
     *
     * @param tradeable the tradeable item to be traded
     */
    public TradeBehaviour(Tradeable tradeable, Item tradeItem) {
        this.tradeable = tradeable;
        this.tradeItem = tradeItem;
    }

    /**
     * Executes the trade action.
     * Applies the trade effect to the actor and returns a description of the trade.
     *
     * @param actor the actor performing the trade
     * @param map the current game map
     * @return a string description of the trade action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        tradeable.applyTradeEffect(actor);
        actor.removeItemFromInventory(tradeItem);
        return actor + " traded " + tradeable + " for " + tradeable.getTradeItem();
    }

    /**
     * Returns a description of the trade action for the menu.
     *
     * @param actor the actor performing the trade
     * @return a string description of the trade action for the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " trades " + tradeable + " for " + tradeable.getTradeItem();
    }
}

