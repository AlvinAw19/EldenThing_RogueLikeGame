package game.pets;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attributes.Status;
import game.behaviours.FollowBehaviour;
import game.behaviours.HealingBehaviour;

/**
 * A HealingUnicorn pet that can follow the player and heal them.
 * This class extends the Pet abstract class and adds specific behaviors for the Healing Unicorn.
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public class HealingUnicorn extends Pet {

    /**
     * Constructor for HealingUnicorn, setting its name, display character, and hit points.
     */
    public HealingUnicorn(){
        super("Healing Unicorn", '^', 150);
    }

    /**
     * Determines the actions that this HealingUnicorn can perform in relation to another actor.
     * If the HealingUnicorn has been chosen as a pet, it will follow the actor and heal them if the actor is the player.
     * If it has not been chosen as a pet, it will allow the actor to choose it if they are player.
     *
     * @param otherActor The actor this HealingUnicorn is interacting with.
     * @param direction The direction of the interaction.
     * @param map The current game map.
     * @return A list of allowable actions for this HealingUnicorn in relation to the other actor.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (this.hasCapability(Status.CHOSEN_PET)) {
            if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                this.behaviours.put(2, new FollowBehaviour(otherActor));
                this.behaviours.put(1, new HealingBehaviour(otherActor));
            }
        }
        else {
            if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                actions.add(new ChoosePetAction(this));
            }
        }

        return actions;
    }
}
