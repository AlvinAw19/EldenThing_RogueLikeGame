package game.shop;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Action for buying an item from a shop.
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public class BuyAction extends Action {
    private final Item item;
    private final Shop shop;
    private final int price;

    /**
     * Constructor to initialize BuyAction with an Item and a shop.
     *
     * @param item  The Item being purchased.
     * @param price The price of the item.
     * @param shop  The shop where the item is being bought.
     */
    public BuyAction(Item item, int price, Shop shop) {
        this.item = item;
        this.price = price;
        this.shop = shop;
    }

    /**
     * Executes the action of buying the item, deducting from the actor's wallet.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return A description of the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getBalance() >= price) {
            actor.deductBalance(price);
            actor.addItemToInventory(item);
            return actor + " buys " + item + " for " + price + " coins.";
        } else {
            return actor + " does not have enough coins to buy " + item + ".";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + item + " for " + price + " gold" +" from " + shop;
    }
}
