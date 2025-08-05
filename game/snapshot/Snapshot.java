package game.snapshot;
import edu.monash.fit2099.engine.actors.Actor;
/**
 * The Snapshot interface allows saving and restoring the state of an Actor.
 * @author Asyraaf Rahman
 */
public interface Snapshot
{
    /**
     * Saves the current state of the specified Actor.
     *
     * @param actor the Actor whose state is to be saved
     */
    void save(Actor actor);

    /**
     * Restores the state of the specified Actor to the last saved state.
     *
     * @param actor the Actor whose state is to be restored
     */
    void restore(Actor actor);
}
