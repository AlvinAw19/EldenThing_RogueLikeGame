package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.*;

import java.util.Random;

/**
 * A Stomp attack behaviour that implements the Behaviour Interface
 * Created by: Somkiet Phromsuwan
 *
 * @author Somkiet Phromsuwan
 */
public class StompBehaviour extends Action implements Behaviour {

    private final Actor TARGET;
    private final int HIT_RATE = 5;
    private final int EXPLOSION_RATE = 10;
    private final int STOMP_DAMAGE = 100;
    private final int EXPLOSION_DAMAGE = 50;


    /**
     * Non default constructor.
     *
     * @param player The actor that will be targeted by the stomp attack
     */
    public StompBehaviour(Actor player) {
        this.TARGET = player;
    }


    /**
     * Executes the stomp attack.
     * Performs the explosion as well
     * @param enemy The actor performing the stomp attack
     * @param map The game map containing the actor and the target
     * @return A string that shows the result of the stomp
     */
    @Override
    public String execute(Actor enemy, GameMap map) {
        String ret = "";

        Random random = new Random();
        if ((random.nextInt(100) < this.HIT_RATE)) {
            this.TARGET.hurt(STOMP_DAMAGE);
            ret += enemy + " successfully hits " + TARGET;
        } else {
            ret += enemy + " misses " + TARGET;
        }

        if (!(TARGET.isConscious())) {
            ret += "\n" + TARGET.unconscious(enemy, map);
        }

        if ((random.nextInt(100) < this.EXPLOSION_RATE)){
            Explosion explosion = new Explosion(EXPLOSION_DAMAGE);
            explosion.execute(enemy, map);
            BurnSurroundings burnSurroundings = new BurnSurroundings();
            burnSurroundings.execute(enemy, map);

            ret += "\n" + enemy + "'s" + " stomp attack results in an explosion, burning the surrounding area";
        }
        return ret;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "";
    }

    /**
     * Determines if the stomp action should be performed.
     * @param actor The actor performing the action
     * @param map The game map containing the actor and the target
     * @return The stomp action if the target is within the actor's surroundings else null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination() == map.locationOf(TARGET)) {
                return this;
            }
        }
        return null;
    }
}
