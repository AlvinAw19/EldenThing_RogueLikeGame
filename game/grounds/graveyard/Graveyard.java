package game.grounds.graveyard;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents a Graveyard ground where enemies like Man-Flies and Spirits spawn.
 * This special terrain continuously spawns enemy entities based on certain probabilities.
 * A {@link Spawner} is used to manage which enemies get spawned.
 *
 * Created by: Aw Shen Yang
 */
public class Graveyard extends Ground{
    Spawner spawner;

    /**
     * Constructor for the Graveyard.
     * Initializes the ground with a character symbol and a {@link Spawner} that
     * determines which enemy to spawn at this location.
     *
     * @param newSpawner the spawner responsible for generating enemies
     */
    public Graveyard(Spawner newSpawner){
        super('n', "Graveyard");
        this.spawner = newSpawner;
    }

    /**
     * This method is called every game tick to potentially spawn an enemy at this location.
     * The {@link Spawner} decides whether or not to spawn an enemy based on probability
     * and the absence of an actor at the current location.
     *
     * @param location the location where the tick is occurring
     */
    @Override
    public void tick(Location location){
        spawner.spawnEnemy(location);
    }
}
