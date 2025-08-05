package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.attributes.Ability;
import game.attributes.Status;
import game.behaviours.FollowBehaviour;
import game.behaviours.StingBehaviour;
import game.behaviours.WanderBehaviour;
import game.remembrances.RemembranceOfTheFurnaceGolem;
import game.weapons.AttackAction;

/**
 * A type of enemy, Man-Fly, that has 50 hit points and can sting the Tarnished.
 * Man-Flies can deal 20 damage with a 25% chance to hit and may poison the Tarnished.
 * If the Tarnished is poisoned, they take 10 poison damage over two turns, which can stack.
 * Man-Flies chase the Tarnished across the map.
 *
 * @author Aw Shen Yang
 */
public class ManFly extends Enemy {
    private final int GOLD_REWARD = 5;

    /**
     * Default constructor for ManFly, initializes with 50 hit points and wander behavior.
     */
    public ManFly() {
        super("Man-Fly", '%', 50);
        this.behaviours.put(999, new WanderBehaviour());
        this.addCapability(Ability.POISON_RESISTANT);
    }

    /**
     * Determines the allowable actions that the other actor can perform on this Man-Fly.
     * Adds an attack action if the other actor is hostile, and sets up behaviors for stinging and following.
     *
     * @param otherActor the other Actor interacting with this Man-Fly
     * @param direction  the direction of the other Actor
     * @param map        the current game map
     * @return a list of Actions that the other actor can perform
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
            this.behaviours.put(1, new StingBehaviour(otherActor));
            this.behaviours.put(2, new FollowBehaviour(otherActor));
        }
        return actions;
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
