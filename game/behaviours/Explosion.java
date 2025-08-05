package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.attributes.Ability;
import game.grounds.Fire;

import java.util.Random;

/**
 * The Explosion class represents an action that causes an explosion
 * around the source (typically an enemy), damaging nearby actors.
 * @author Somkiet Phromsuwan
 */
public class Explosion extends Action {

    public final int EXPLOSION_DAMAGE;

    /**
     * Constructor for the Explosion class.
     * @param explosionDamage the amount of damage each actor in range will take from the explosion
     */
    public Explosion(int explosionDamage) {
        this.EXPLOSION_DAMAGE = explosionDamage;
    }

    /**
     * Executes the explosion, dealing damage to all actors in adjacent locations.
     * For each adjacent location, if an actor is present, they will take the specified damage.
     * @param enemy the actor triggering the explosion
     * @param map the map the explosion occurs on
     * @return an empty string, as this action does not return a description for the menu
     */
    @Override
    public String execute(Actor enemy, GameMap map) {
        Display display = new Display();
        for (Exit exit : map.locationOf(enemy).getExits()) {
            Location coordinate = exit.getDestination();
            if (coordinate.containsAnActor()) {
                Actor actor = coordinate.getActor();
                actor.hurt(EXPLOSION_DAMAGE);
                if (!(actor.isConscious())){
                    display.println(actor.unconscious(enemy,map));
                }
            }
        }
        return "";
    }

    /**
     * Provides a description for the menu when this action is selected.
     * Since the explosion is automatic, no menu description is required.
     * @param actor the actor performing the action
     * @return an empty string, as this action doesn't appear directly in a menu
     */
    @Override
    public String menuDescription(Actor actor){
        return "";
    }

}
