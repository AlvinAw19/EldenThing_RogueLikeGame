package game.snapshot;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.attributes.NewBaseActorAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * The ActorAttributeSnapshot class implements the Snapshot interface,
 * allowing for saving and restoring an Actor's attributes.
 *
 * This class maintains a stack of attribute states for an Actor.
 * When saving, it creates a copy of the current attributes and
 * pushes it onto the stack.
 *
 * Restoration retrieves the most recent snapshot from the stack
 * and updates the Actor's attributes accordingly.
 * @author Asyraaf Rahman
 */
public class ActorAttributeSnapshot implements Snapshot
{
    private final Stack<Map<Enum<?>, Integer>> snapshotStack = new Stack<>();

    @Override
    public void save(Actor actor) {
        Map<Enum<?>, Integer> copiedAttributes = new HashMap<>();

        copyAttributes(copiedAttributes, actor, BaseActorAttributes.values());
        copyAttributes(copiedAttributes, actor, NewBaseActorAttributes.values());

        snapshotStack.push(copiedAttributes);
    }

    /**
     * Copies specified attributes from the actor into the provided attributes map,
     * if the actor has those attributes.
     *
     * @param attributesMap the map where attribute values will be stored
     * @param actor the actor from whom attributes are copied
     * @param attributes the array of attributes to check and copy
     */
    private void copyAttributes(Map<Enum<?>, Integer> attributesMap, Actor actor, Enum<?>[] attributes) {
        for (Enum<?> attr : attributes) {
            if (actor.hasAttribute(attr)) {
                attributesMap.put(attr, actor.getAttribute(attr));
            }
        }
    }


    @Override
    public void restore(Actor actor) {
        if (!snapshotStack.isEmpty()) {
            Map<Enum<?>, Integer> savedAttributes = snapshotStack.pop();

            for (Map.Entry<Enum<?>, Integer> entry : savedAttributes.entrySet()) {
                actor.modifyAttribute(entry.getKey(), ActorAttributeOperations.UPDATE, entry.getValue());
            }
        }
    }
}
