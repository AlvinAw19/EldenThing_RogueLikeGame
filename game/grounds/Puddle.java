package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Scarab;
import game.attributes.Ability;
import game.consumables.Consumable;
import game.consumables.ConsumeAction;

import java.util.Random;

/**
 * A class that represents a puddle of water on the ground.
 * The puddle gives mana when consumed and has a chance to spawn Scarabs nearby.
 * @author Somkiet Phromsuwan
 * Modified by: Aw Shen Yang
 */
public class Puddle extends Ground implements Consumable {

    private final int MANA_GAINED = 5;
    private final int SCARAB_SPAWN_CHANCE = 10; // 10% chance to spawn a Scarab

    public Puddle() {
        super('~', "Puddle");
        this.addCapability(Ability.FIRE_RESISTANT);
        this.addCapability(Ability.WEAKNESS_ELECTRIC);
        this.addCapability(Ability.SLIPPERY);
    }

    @Override
    public String consume(Actor player, Consumable item, GameMap map) {
        // Increase the player's mana
        player.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.INCREASE, MANA_GAINED);

        // Random chance to spawn a Scarab nearby
        Random random = new Random();
        if (random.nextInt(100) < SCARAB_SPAWN_CHANCE) {
            Location puddleLocation = map.locationOf(player);
            for (Exit exit : puddleLocation.getExits()) {
                if (!exit.getDestination().containsAnActor()) {
                    exit.getDestination().addActor(new Scarab());
                    break;
                }
            }
        }

        return player + " consumed water from " + this;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        // Allow the actor to consume the puddle if they are standing on it
        if (location.getActor() == actor) {
            actions.add(new ConsumeAction(actor, this));
        }
        return actions;
    }
}
