package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.Tradeable;
import game.remembrances.RemembranceOfTheDancingLion;
import game.remembrances.RemembranceOfTheFurnaceGolem;
import game.behaviours.TradeBehaviour;
import game.attributes.Ability;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Suspicious Trader.
 * The trader does nothing during its turn but can trade items with other actors.
 * Author: Au Jenq
 */

public class SuspiciousTrader extends Actor {
    /**
     * Default constructor for Suspicious Trader.
     * Initializes the trader with the IMMUNE capability.
     */
    private List<Tradeable> tradeables = setUpTradeables();
    public SuspiciousTrader() {
        super("Suspicious Trader", 'ඞ', 1000);
        this.addCapability(Ability.IMMUNE);
        this.tradeables = setUpTradeables();
    }

    /**
     * Defines the actions the trader performs during its turn.
     * The trader does nothing during its turn.
     *
     * @param actions the list of possible actions
     * @param lastAction the last action performed
     * @param map the current game map
     * @param display the display to show messages
     * @return the action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Sets up the tradeable items for the trader.
     * Returns a list of tradeable items.
     *
     * @return a list of tradeable items
     */
    public List<Tradeable> setUpTradeables() {
        List<Tradeable> tradeables = new ArrayList<>();
        tradeables.add(new RemembranceOfTheDancingLion());
        tradeables.add(new RemembranceOfTheFurnaceGolem());
        return tradeables;
    }


    /**
     * Determines the actions that can be performed by other actors on the trader.
     * Adds trade actions if the other actor has items that can be traded.
     *
     * @param otherActor the Actor that might be performing the action
     * @param direction the direction of the other Actor
     * @param map the current game map
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        for (Item item : otherActor.getItemInventory()) {
            for (Tradeable tradeable : tradeables) {
                if (item.toString().equals(tradeable.toString())){
                    actions.add(new TradeBehaviour(tradeable, item));
                }
            }
        }
        return actions;
    }
}
