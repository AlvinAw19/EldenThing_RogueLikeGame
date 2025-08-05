package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.weapons.BareFist;
import game.utility.FancyMessage;
import game.attributes.Ability;
import game.attributes.NewBaseActorAttributes;
import game.attributes.Status;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Somkiet Phromsuwan
 *
 */
public class Player extends Actor {

    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints, int mana, int strength) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.CAN_ENTER_FLOOR);
        this.addCapability(Ability.CAN_USE_WEAPON);
        this.addCapability(Ability.DIVINE_POWER);
        this.addCapability(Ability.WIND_DIVINE_POWER);
        this.setIntrinsicWeapon(new BareFist());
        this.addAttribute(BaseActorAttributes.MANA, new BaseActorAttribute(mana));
        this.addAttribute(NewBaseActorAttributes.STRENGTH, new BaseActorAttribute(strength));
        this.addCapability(Ability.IMMUNE);
    }

    /**
     * Returns the player's current mana.
     *
     * @return the current mana of the player
     */
    public int getMana() {
        return this.getAttribute(BaseActorAttributes.MANA);
    }


    /**
     * Returns the player's current strength.
     *
     * @return the current strength of the player
     */
    public int getStrength() {
        return this.getAttribute(NewBaseActorAttributes.STRENGTH);
    }

    /**
     * Returns a fancy message indicating that the player has died
     *
     * @param actor the perpetrator
     * @param map   where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map){
        return FancyMessage.YOU_DIED + super.unconscious(actor, map);
    }

    @Override
    public String unconscious(GameMap map){
        return FancyMessage.YOU_DIED + super.unconscious(map);
    }

    /**
     * Displaying player information and menu per turn
     * Returns last action if it is not null
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed or the menu
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        display.println(this.toString());
        display.println("Mana: (" + getMana() + "/" + this.getAttributeMaximum(BaseActorAttributes.MANA) + ")");
        display.println("Strength: " + getStrength());
        display.println("Gold: " + getBalance());


        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }
}
