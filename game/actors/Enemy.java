package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Map;
import java.util.TreeMap;

/**
 * Abstract base class for all enemy types in the game.
 * This class defines common enemy behaviors, such as action selection based on priority.
 * Enemies have a set of behaviors mapped by priority, and they act based on the highest priority behavior.
 *
 * @author Somkiet Phromsuwan, Aw Shen Yang
 */
public abstract class Enemy extends Actor {

    /**
     * A map that stores the behavioral priorities for the enemy.
     * The key represents the priority level, with lower numbers indicating higher priority.
     */
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * Constructor to create an enemy with a specified name, display character, and hit points.
     *
     * @param name        the name of the enemy
     * @param displayChar the character representing the enemy on the map
     * @param hitPoints   the hit points (health) of the enemy
     */
    public Enemy(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
    }

    /**
     * Determines the action the enemy will take during its turn by iterating over behaviors by priority.
     *
     * @param actions    a collection of possible Actions for the enemy
     * @param lastAction the Action this enemy took last turn
     * @param map        the game map containing the enemy
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed, or a DoNothingAction if no behaviors are applicable
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
     * Determines the allowable actions that another actor can perform on this enemy.
     *
     * @param otherActor the other Actor interacting with this enemy
     * @param direction  the direction of the other Actor
     * @param map        the current game map
     * @return a list of Actions that the other actor can perform
     */
    @Override
    public abstract ActionList allowableActions(Actor otherActor, String direction, GameMap map);
}
