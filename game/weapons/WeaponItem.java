package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.attributes.Ability;
import game.shop.BuyableItem;
import game.weapons.weaponart.WeaponArt;

import java.util.Random;

/**
 * Class representing items that can be used as a weapon.
 * Modified by: Somkiet Phromsuwan
 */
public abstract class WeaponItem extends BuyableItem implements Weapon {
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private int damage;
    private int hitRate;
    private final String verb;
    private float damageMultiplier;
    private int strengthPoint;
    private WeaponArt weaponArt;
    private final int price;

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target
     * @param strengthPoint the strength point required to wield this weapon
     * @param weaponArt   the weapon art associated with this weapon, or null if none
     * @param price       the price of the weapon item
     */
    public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate, int strengthPoint,
                      WeaponArt weaponArt, int price) {
        super(name, displayChar);
        this.damage = damage;
        this.verb = verb;
        this.hitRate = hitRate;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
        this.strengthPoint = strengthPoint;
        this.weaponArt = weaponArt;
        this.price = price;
    }
    /**
     * Executes an attack with the weapon.
     * @param attacker The actor using the weapon
     * @param target   The actor to hit
     * @param map      The game map
     * @return A string showing the result of the attack
     */
    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();

        String attack = "";

        if (!(rand.nextInt(100) < this.hitRate)) {
            attack = attacker + " misses " + target + ".";
        }
        else
        {
            target.hurt(Math.round(damage * damageMultiplier));
            attack = String.format("%s %s %s for %d damage", attacker, verb, target, damage);
        }

        if (this.weaponArt != null && canUseArt(attacker)) {
            attack += "\n" + weaponArt.use(attacker, target, map);
            attacker.modifyAttribute(BaseActorAttributes.MANA, ActorAttributeOperations.DECREASE, weaponArt.manaCost());
        }

        return attack;
    }

    /**
     * Helper method to check if the attacker has enough mana for a weapon art
     * @param attacker The actor attempting to use the WeaponArt.
     * @return true if the attacker has enough mana points, false otherwise.
     */
    private boolean canUseArt(Actor attacker) {
        return attacker.getAttribute(BaseActorAttributes.MANA) >= weaponArt.manaCost();
    }

    /**
     * Method to check if the weapon has WeaponArt
     * @return false if WeaponArt is null
     * @return true if it has WeaponArt
     */
    public boolean hasWeaponArt(){
        if (this.weaponArt == null){
            return false;
        }
        return true;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        if (otherActor.hasCapability(Ability.IMMUNE)) {
            return new ActionList();
        }
        ActionList actions = new ActionList(new AttackAction(otherActor, location.toString(),this));
        return actions;
    }

    @Override
    public String toString() {
        if (weaponArt == null) {
            return super.toString();
        }
        return super.toString() + "(" + weaponArt.toString() + ")";
    }

    @Override
    public int getPrice(){
        return price;
    }
}
