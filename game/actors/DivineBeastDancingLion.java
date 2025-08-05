package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attributes.Ability;
import game.attributes.Status;
import game.behaviours.*;
import game.remembrances.RemembranceOfTheDancingLion;
import game.weapons.AttackAction;
import edu.monash.fit2099.engine.positions.Location;
import java.util.List;
import java.util.ArrayList;
import game.grounds.Gate;

/**
 * The DivineBeastDancingLion is an Enemy that utilizes divine powers and
 * exhibits specific behaviors, such as wandering and attacking hostile actors.
 * It has special unconscious behavior where it creates a gate and leaves behind
 * a remembrance item when defeated.
 * @author Aw Shen Yang
 */
public class DivineBeastDancingLion extends Enemy {
    private GameMap BeluratTowerNorthGate; // Reference to the grave site map
    private final int GOLD_REWARD = 100;

    /**
     * Constructor for the DivineBeastDancingLion
     * @param map GameMap instance representing the Belurat Tower map
     */
    public DivineBeastDancingLion(GameMap map) {
        super("Divine Beast Dancing Lion", 'S', 10000);
        this.behaviours.put(999, new WanderBehaviour()); // Default wander behavior
        this.addCapability(Ability.DIVINE_POWER); // Add divine power capability
        this.addCapability(Ability.WIND_DIVINE_POWER); // Start with Wind Divine Power
        this.BeluratTowerNorthGate = map; // Assign the Belurat Tower map
    }

    /**
     * Define the allowable actions that other actors can perform on the lion.
     * If the actor is hostile, it can attack the lion.
     * @param otherActor the actor performing the action
     * @param direction the direction of the action
     * @param map the GameMap containing the lion
     * @return ActionList of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction)); // Allow the hostile actor to attack
            // Switch to divine power + biting and following behaviors when hostile
            this.behaviours.put(1, new DivinePowerBiteBehaviour(otherActor));
            this.behaviours.put(2, new FollowBehaviour(otherActor));
        }
        return actions;
    }

    /**
     * Defines what happens when the DivineBeastDancingLion becomes unconscious.
     * It leaves a gate and a remembrance item at its location.
     * @param actor the actor that defeated the lion
     * @param map the GameMap where the lion resides
     * @return an empty string after processing unconscious behavior
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        // Store the location before the lion is removed from the map
        Location location = map.locationOf(this);
        if (location != null) {
            // Specify the gravesite locations for the Gate
            List<Location> BelurateTowerNorthGateDestinationList = new ArrayList<>();
            BelurateTowerNorthGateDestinationList.add(BeluratTowerNorthGate.at(23, 0)); // Set a location for the gate

            // Set a Gate on the ground at the lion's current location
            location.setGround(new Gate(BelurateTowerNorthGateDestinationList));
            // Leave behind the Remembrance of the Dancing Lion item
            location.addItem(new RemembranceOfTheDancingLion());
            if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                actor.addBalance(GOLD_REWARD); // Add balance to the actor if hostile
            }
        }
        // Remove the lion from the map (unconscious state)
        map.removeActor(this);
        return this + " met their demise in the hand of " + actor;
    }
}
