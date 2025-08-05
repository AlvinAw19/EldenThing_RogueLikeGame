package game.grounds.graveyard;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Spirit;

/**
 * Responsible for spawning a {@link Spirit} enemy at a given location.
 * The enemy is only spawned if the location doesn't already contain an actor,
 * and there's a 20% chance for the spawning to occur during each tick.
 *
 * Created by: Aw Shen Yang
 */
public class SpiritSpawner implements Spawner {
    /**
     * Spawns a {@link Spirit} at the specified location based on a 20% probability.
     * The enemy is only spawned if no actor is already present at the location.
     *
     * @param location the location where the Spirit might spawn
     */
    @Override
    public void spawnEnemy(Location location) {
        if (Math.random() <= 0.2 && !location.containsAnActor()) {
            location.addActor(new Spirit());
        }
    }

}