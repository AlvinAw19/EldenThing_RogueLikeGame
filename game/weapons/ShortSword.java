package game.weapons;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import game.attributes.Ability;
import game.attributes.NewBaseActorAttributes;
import game.weapons.weaponart.WeaponArt;

public class ShortSword extends WeaponItem {
    private static final int STRENGTH_REQUIRED = 10;

    public ShortSword(WeaponArt weaponArt) {
        super("Short Sword", '!', 100, "slashes", 75, STRENGTH_REQUIRED, weaponArt, 20);
    }

    /**
     * Determines if the weapon can be picked up by the actor.
     * @param actor The actor attempting to pick up the weapon
     * @return An action to pick up the weapon or null
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        if (actor.hasCapability(Ability.CAN_USE_WEAPON)){
            int player_strength = actor.getAttribute(NewBaseActorAttributes.STRENGTH);
            if (player_strength >= STRENGTH_REQUIRED) {
                return new PickUpAction(this);
            }
        }
        return null;
    }
}
