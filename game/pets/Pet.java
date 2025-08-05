package game.pets;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attributes.Ability;

import java.util.Map;
import java.util.TreeMap;

/**
 * An abstract class representing a pet in the game.
 * Pets are actors that can have various behaviors and can perform actions in the game world.
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public abstract class Pet extends Actor {

    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * Constructor for creating a pet with a name, display character, and hit points.
     *
     * @param name The name of the pet.
     * @param displayChar The character used to represent the pet in the game.
     * @param hitPoints The initial hit points of the pet.
     */
    public Pet(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.addCapability(Ability.CAN_ENTER_FLOOR);
        this.addCapability(Ability.IMMUNE);
    }

    /**
     * Executes the pet's turn by checking its behaviours and performing the first available action.
     *
     * @param actions The list of actions that can be performed this turn.
     * @param lastAction The last action that was performed.
     * @param map The game map on which the pet is located.
     * @param display The display for showing output to the player.
     * @return The action to be performed by the pet, or a DoNothingAction if no action is available.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Returns a list of allowable actions for the pet based on the context of another actor and the direction.
     *
     * @param otherActor The actor that the pet interacts with.
     * @param direction The direction in which the interaction is attempted.
     * @param map The game map where the interaction occurs.
     * @return An ActionList containing allowable actions for the pet.
     */
    @Override
    public abstract ActionList allowableActions(Actor otherActor, String direction, GameMap map);
}
