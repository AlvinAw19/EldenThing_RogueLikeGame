package game.weapons.weaponart;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class representing the Lifesteal weapon art that heals the attacker for every attack
 * Created by:
 * @author Asyraaf Rahman
 */
public class Lifesteal implements WeaponArt {
    private static final int DEFAULT_HEAL_AMOUNT = 20;
    private static final int DEFAULT_MANA_COST = 10;
    private int healAmount;
    private int manaCost;

    /**
     * Constructor with custom heal amount and mana cost.
     *
     * @param healAmount The amount of health the attacker will heal.
     * @param manaCost   The mana cost for using Lifesteal.
     */
    public Lifesteal(int healAmount, int manaCost) {
        this.healAmount = healAmount;
        this.manaCost = manaCost;
    }

    /**
     * Constructor with default values for heal amount and mana cost.
     */
    public Lifesteal() {
        this(DEFAULT_HEAL_AMOUNT, DEFAULT_MANA_COST);
    }

    @Override
    public String use(Actor attacker, Actor target, GameMap map) {
        attacker.heal(healAmount);
        return "Attacking " + target + " heals " + attacker;
    }

    @Override
    public int manaCost() {
        return manaCost;
    }

    @Override
    public String toString() {
        return "Lifesteal";
    }
}