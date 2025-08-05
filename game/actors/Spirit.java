package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.attributes.Status;
import game.behaviours.WanderBehaviour;
import game.weapons.AttackAction;
import game.weapons.BareFist;

/**
 * Spirit enemy in the game, with 100 hit points and attacks Tarnished when in range.
 * Spirits don't follow the player but deal 25 damage with a 50% chance to hit.
 *
 * @author Aw Shen Yang
 */
public class Spirit extends Enemy {
    private final int GOLD_REWARD = 5;

    /**
     * Default constructor for Spirit, initializes with 100 hit points and wander behavior.
     * The Spirit uses a BareFist as its intrinsic weapon.
     */
    public Spirit() {
        super("Spirit", '&', 100);
        this.behaviours.put(999, new WanderBehaviour());
        this.setIntrinsicWeapon(new BareFist());
    }

    /**
     * Determines the allowable actions for the other actor to perform on this Spirit.
     * Adds an attack action if the other actor is hostile.
     *
     * @param otherActor the other Actor interacting with this Spirit
     * @param direction  the direction of the other Actor
     * @param map        the current game map
     * @return a list of Actions that the other actor can perform
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Determines the action this Spirit will take on its turn.
     * The Spirit will attack any actor with the Status.HOSTILE_TO_ENEMY in an adjacent location.
     *
     * @param actions    a collection of possible Actions for this Spirit
     * @param lastAction the Action this Spirit took last turn
     * @param map        the game map containing this Spirit
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed by this Spirit
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Exit exit : map.locationOf(this).getExits()) {
            Actor targetActor = map.getActorAt(exit.getDestination());
            if (exit.getDestination() == map.locationOf(targetActor) && targetActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                return new AttackAction(targetActor, exit.getName());
            }
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actor.addBalance(GOLD_REWARD);
        }
        map.removeActor(this);
        return this + " met their demise in the hand of " + actor;
    }
}
