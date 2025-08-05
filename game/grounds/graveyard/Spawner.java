package game.grounds.graveyard;

import edu.monash.fit2099.engine.positions.Location;

/**
 * The interface for a generic spawner.
 * Spawners are responsible for generating enemies at specific locations based on certain conditions.
 * Each spawner implementation can spawn different types of enemies.
 *
 * Created by: Aw Shen Yang
 */
public interface Spawner {
    /**
     * A method to spawn any enemy.
     *
     * @param location Location to spawn the enemy.
     * @return The spawned actor or null if spawning was not successful.
     */
    void spawnEnemy(Location location);
}
