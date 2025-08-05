package game.shop;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.shop.Gold;

/**
 * A class representing the action of picking up Gold from the ground.
 * This action adds the gold to the actor's balance and removes it from the map.
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public class PickUpGoldAction extends PickUpAction {

    /**
     * The Gold item to be picked up.
     */
    private final Gold gold;

    /**
     * Constructor to create a PickUpGoldAction for a specific Gold item.
     *
     * @param gold The gold to be picked up
     */
    public PickUpGoldAction(Gold gold){
        super(gold);
        this.gold = gold;
    }

    /**
     * Executes the action of picking up the gold. Adds the gold amount to the actor's balance
     * and removes the gold from the map.
     *
     * @param actor The actor picking up the gold
     * @param map The map where the actor is located
     * @return A string describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map){
        int goldAmount = gold.getGoldAmount();
        actor.addBalance(goldAmount);
        map.locationOf(actor).removeItem(gold);
        return actor + " picks up the " + gold + " and obtained " + goldAmount+ " Gold";
    }
}
