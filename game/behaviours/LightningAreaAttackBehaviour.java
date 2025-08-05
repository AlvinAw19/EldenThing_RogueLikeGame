package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.attributes.Ability;

/**
 * A behaviour that allows an actor to perform a lightning area attack.
 * This attack damages all adjacent enemies.
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public class LightningAreaAttackBehaviour extends Action implements Behaviour {

    private final Actor ENEMY;
    private final int AREA_DAMAGE = 50;

    /**
     * Constructor to create a LightningAreaAttackBehaviour targeting a specific enemy.
     *
     * @param enemy The enemy actor targeted by the area attack.
     */
    public LightningAreaAttackBehaviour(Actor enemy) {
        this.ENEMY = enemy;
    }

    /**
     * Executes the lightning area attack, dealing damage to all adjacent enemies
     * that are not immune.
     *
     * @param actor The actor performing the attack.
     * @param map The map where the actor is located.
     * @return A string describing the result of the action, including damage dealt.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String ret = "";
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location coordinate = exit.getDestination();
            if (coordinate.containsAnActor()) {
                Actor enemy = coordinate.getActor();
                if (!enemy.hasCapability(Ability.IMMUNE)) {
                    enemy.hurt(AREA_DAMAGE);
                    ret += enemy + " takes " + AREA_DAMAGE + " damage resulting from " + actor + "'s area attack";
                    if (!enemy.isConscious()) {
                        ret += '\n' + enemy.unconscious(actor, map);
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "";
    }

    /**
     * Determines if this action should be performed based on the location of the actor
     * and the presence of the targeted enemy.
     *
     * @param actor The actor performing the action.
     * @param map The map where the actor is located.
     * @return This action if the targeted enemy is adjacent; null otherwise.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination() == map.locationOf(ENEMY)) {
                return this;
            }
        }
        return null;
    }
}
