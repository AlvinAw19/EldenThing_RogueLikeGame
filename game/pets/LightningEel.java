package game.pets;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attributes.Ability;
import game.attributes.Status;
import game.behaviours.FollowBehaviour;
import game.behaviours.LightningAreaAttackBehaviour;

/**
 * A LightningEel pet that can follow the player and perform area attacks on enemies.
 * This class extends the Pet abstract class and adds specific behaviors for the Lightning Eel.
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public class LightningEel extends Pet {

    /**
     * Constructor for LightningEel, setting its name, display character, and hit points.
     */
    public LightningEel(){
        super("Lightning Eel", 'Z', 120);
    }

    /**
     * Determines the actions that this LightningEel can perform in relation to another actor.
     * If the LightningEel has been chosen as a pet, it will either follow the actor if the actor is the player,
     * or perform a lightning area attack if the other actor is not immune. If it has not been chosen as a pet,
     * it will allow the actor to choose it if they are the player.
     *
     * @param otherActor The actor this LightningEel is interacting with.
     * @param direction The direction of the interaction.
     * @param map The current game map.
     * @return A list of allowable actions for this LightningEel in relation to the other actor.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (this.hasCapability(Status.CHOSEN_PET)) {
            if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                this.behaviours.put(2, new FollowBehaviour(otherActor));
            } else if (!otherActor.hasCapability(Ability.IMMUNE)){
                this.behaviours.put(1, new LightningAreaAttackBehaviour(otherActor));
            }
        } else {
            if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                actions.add(new ChoosePetAction(this));
            }
        }

        return actions;
    }
}

