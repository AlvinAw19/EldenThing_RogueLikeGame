package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.attributes.Status;
import game.statusEffects.ScarabBuffEffect;
import game.attributes.Ability;
import game.behaviours.Explosion;
import game.behaviours.WanderBehaviour;
import game.consumables.Consumable;
import game.consumables.ConsumeAction;
import game.consumables.CrimsonTear;
import game.weapons.AttackAction;

/**
 * The Scarab class represents an enemy in the game.
 * Scarabs wander around the map, and when defeated, they explode,
 * dropping a Crimson Tear item. Players can also consume the Scarab
 * to temporarily boost their health and mana.
 * Created by:
 * @author Somkiet Phromsuwan
 */
public class Scarab extends Enemy implements Consumable {

    private Explosion explosion;
    private final int EXPLOSION_DAMAGE = 25;
    private final int GOLD_REWARD = 5;

    /**
     * Constructor for the Scarab class.
     * Initializes the scarab's health and behavior, and assigns it the
     * poison resistance ability.
     */
    public Scarab(){
        super("Scarab", 'b', 25);
        this.behaviours.put(999, new WanderBehaviour());
        this.explosion = new Explosion(EXPLOSION_DAMAGE);
        this.addCapability(Ability.POISON_RESISTANT);
    }

    /**
     * Handles the logic for when the Scarab becomes unconscious.
     * This method triggers the scarab's explosion and drops a Crimson Tear item
     * at its location. The scarab is also removed from the map after its death.
     * @param actor the actor responsible for making the scarab unconscious
     * @param map the game map the scarab is on
     * @return a string describing the scarab's demise
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Display display = new Display();
        explosion.execute(this, map);
        display.println(this + " explodes!");
        map.locationOf(this).addItem(new CrimsonTear());
        map.removeActor(this);
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actor.addBalance(GOLD_REWARD);
        }
        return this + " met their demise in the hand of " + actor;
    }

    /**
     * Allows the player to consume the Scarab.
     * Consuming the scarab provides a temporary buff to the player's maximum health
     * and mana for a certain number of turns. The scarab is removed from the map
     * after consumption.
     * @param actor the actor consuming the scarab
     * @param item the scarab being consumed
     * @param map the game map the scarab is on
     * @return a string indicating that the scarab has been consumed
     */
    @Override
    public String consume(Actor actor, Consumable item, GameMap map) {
        int initialMaxHitPoints = actor.getAttributeMaximum(BaseActorAttributes.HEALTH);
        int initialMaxMana = actor.getAttributeMaximum(BaseActorAttributes.MANA);
        actor.addStatusEffect(new ScarabBuffEffect(initialMaxHitPoints, initialMaxMana));
        map.removeActor(this);
        return item + " consumed by " + actor + ".";
    }


    /**
     * Returns a list of actions the player can perform on the scarab.
     * Players can either attack the scarab or consume it when adjacent to it.
     * @param otherActor the actor interacting with the scarab
     * @param direction the direction of the scarab relative to the actor
     * @param map the game map the scarab is on
     * @return a list of actions the actor can perform on the scarab
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(otherActor, this));
        actions.add(new AttackAction(this,direction));
        return actions;
    }
}
