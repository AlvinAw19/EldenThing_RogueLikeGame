package game.shop;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;

/**
 * A class representing Gold, which can be picked up and used as currency.
 * Gold is represented as an item in the game with a specific amount.
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public class Gold extends Item {

    /**
     * The amount of gold this object represents.
     */
    private final int goldAmount;

    /**
     * Constructor to create a Gold item with a specified amount.
     *
     * @param goldAmount The amount of gold this object holds
     */
    public Gold(int goldAmount){
        super("Gold",'g',true);
        this.goldAmount = goldAmount;
    }

    /**
     * Gets the amount of gold in this object.
     *
     * @return The amount of gold
     */
    public int getGoldAmount(){
        return goldAmount;
    }

    /**
     * Returns the action that allows the Actor to pick up this Gold.
     *
     * @param actor The actor picking up the gold
     * @return A new PickUpGoldAction that allows the actor to pick up the gold
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor){
        return new PickUpGoldAction(this);
    }
}
