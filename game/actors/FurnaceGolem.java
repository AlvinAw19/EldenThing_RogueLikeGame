package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.remembrances.RemembranceOfTheFurnaceGolem;
import game.weapons.AttackAction;
import game.attributes.Ability;
import game.attributes.Status;
import game.behaviours.FollowBehaviour;
import game.behaviours.StompBehaviour;
import game.behaviours.WanderBehaviour;

/**
 * Class representing the Furnace Golem
 * It can wander around the map, stomp actors and follow hostile actors.
 * @author Adrian Kristanto
 */
public class FurnaceGolem extends Enemy {
    private final int GOLD_REWARD = 20;

    /**
     * A default constructor for Furnace Golem with a name, display character, and hit points.
     * Initializes the golem with the WanderBehaviour and the FIRE_RESISTANT capability.
     */
    public FurnaceGolem() {
        super("Furnace Golem", 'A', 1000);
        this.behaviours.put(999, new WanderBehaviour());
        this.addCapability(Ability.FIRE_RESISTANT);
    }

    /**
     * Returns the list of allowable actions for the Furnace Golem.
     *
     * @param otherActor the other Actor on the map that has actions performed on
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
            this.behaviours.put(1, new StompBehaviour(otherActor));
            this.behaviours.put(2, new FollowBehaviour(otherActor));
        }
        return actions;
    }

    /**
     * Handles the unconscious state of the Furnace Golem.
     * Drops a Remembrance of the Furnace Golem item at the golem's location.
     *
     * @param map the map where the actor fell unconscious
     * @return A string indicating the golem ceased to exist.
     */
    @Override
    public String unconscious(GameMap map) {
        // Store the location before the golem is removed from the map
        Location location = map.locationOf(this);
        if (location != null) {
            location.addItem(new RemembranceOfTheFurnaceGolem());
        }
        map.removeActor(this);
        return this + " ceased to exist.";
    }

    /**
     * Handles the unconscious state of the Furnace Golem when defeated by another actor.
     * Drops a Remembrance of the Furnace Golem item at the golem's location and rewards the actor with gold.
     *
     * @param actor the actor that defeated the golem
     * @param map   the map where the actor fell unconscious
     * @return A string indicating the golem met its demise.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        // Store the location before the golem is removed from the map
        Location location = map.locationOf(this);
        if (location != null) {
            location.addItem(new RemembranceOfTheFurnaceGolem());
            if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                actor.addBalance(GOLD_REWARD);
            }
        }
        map.removeActor(this);
        return this + " met their demise in the hand of " + actor;
    }
}

