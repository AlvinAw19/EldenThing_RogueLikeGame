package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A behaviour that allows an actor to heal another actor (the player) for a specified amount of hit points.
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public class HealingBehaviour extends Action implements Behaviour {

    private final Actor PLAYER;
    private final int HEAL_AMOUNT = 10;

    /**
     * Constructor to create a HealingBehaviour targeting a specific player.
     *
     * @param player The actor that will be healed by this behaviour.
     */
    public HealingBehaviour(Actor player) {
        this.PLAYER = player;
    }

    /**
     * Executes the healing action, restoring hit points to the targeted player actor.
     *
     * @param actor The actor performing the healing action.
     * @param map The map where the actor is located.
     * @return A string describing the result of the action, including the amount healed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String ret = "";
        PLAYER.heal(HEAL_AMOUNT);
        ret += actor + " heals " + PLAYER + " for " + HEAL_AMOUNT + " hit points";
        return ret;
    }


    @Override
    public String menuDescription(Actor actor) {
        return "";
    }

    /**
     * Determines if this healing action should be performed based on the location of the actor
     * and the presence of the targeted player.
     *
     * @param actor The actor performing the action.
     * @param map The map where the actor is located.
     * @return This action if the player is adjacent; null otherwise.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination() == map.locationOf(PLAYER)) {
                return this;
            }
        }
        return null;
    }
}

