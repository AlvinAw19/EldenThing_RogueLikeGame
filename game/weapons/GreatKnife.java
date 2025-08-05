package game.weapons;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import game.attributes.Ability;
import game.attributes.NewBaseActorAttributes;
import game.weapons.weaponart.WeaponArt;

public class GreatKnife extends WeaponItem {
    private static final int STRENGTH_REQUIRED = 5;

    public GreatKnife(WeaponArt weaponArt)
    {
        super("Great Knife", '†', 75, "stabs", 60, STRENGTH_REQUIRED, weaponArt, 15);
    }

    /**2
     *
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
