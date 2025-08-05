package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.attributes.Ability;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Somkiet Phromsuwan
 *
 */
public class Floor extends Ground {
    public Floor() {
        super('_', "Floor");
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Ability.CAN_ENTER_FLOOR);

    }
}
