package game.grounds.graveyard;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.ManFly;

/**
 * Responsible for spawning a {@link ManFly} enemy at a given location.
 * The enemy is only spawned if the location doesn't already contain an actor,
 * and there's a 15% chance for the spawning to occur during each tick.
 *
 * Created by: Aw Shen Yang
 */
public class ManFlySpawner implements Spawner {

    /**
     * Spawns a {@link ManFly} at the specified location based on a 15% probability.
     * The enemy is only spawned if no actor is already present at the location.
     *
     * @param location the location where the Man-Fly might spawn
     */
    @Override
    public void spawnEnemy(Location location) {
        if (Math.random() <= 0.15 && !location.containsAnActor()) {
            location.addActor(new ManFly());
        }
    }
}