package game.pets;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attributes.Status;

/**
 * An action that allows an actor to choose a pet companion.
 * This action updates the actor's status to indicate that they have chosen a pet.
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public class ChoosePetAction extends Action {
    private Pet pet;

    /**
     * Constructor for creating a ChoosePetAction.
     *
     * @param pet The pet to be chosen as a companion.
     */
    public ChoosePetAction(Pet pet) {
        this.pet = pet;
    }

    /**
     * Executes the action of choosing a pet.
     * If the actor already has a pet, it will not allow them to choose another one.
     * If successful, it updates the actor and the pet's capabilities.
     *
     * @param actor The actor choosing the pet.
     * @param map The game map where the action takes place.
     * @return A string describing the outcome of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HAS_PET)){
            return actor + " already has a pet.";
        }
        actor.addCapability(Status.HAS_PET);
        pet.addCapability(Status.CHOSEN_PET);
        return actor + " has chosen " + pet + " as their pet companion.";
    }

    /**
     * Returns a description of the action for the menu.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action in the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Choose " + pet + " as your pet companion";
    }
}
