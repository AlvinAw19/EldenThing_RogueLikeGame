package game.divinepower;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Display;
import game.attributes.Ability;
import game.behaviours.Explosion;

/**
 * Represents the Lightning Divine Power which strikes the target and deals damage.
 * If the target is on electric-weak ground, additional damage is dealt.
 * @author Aw Shen Yang
 */
public class LightningDivinePower implements DivinePower {
    private Explosion explosion;
    private final int EXPLOSION_DAMAGE = 50;
    private final double FROST_DIVINE_POWER_CHANCE = 0.4;
    private final double WIND_DIVINE_POWER_CHANCE = 0.4;

    @Override
    public void performSpecialAttack(Actor enemy, Actor target, GameMap map) {
        Display display = new Display();
        display.println("The " + enemy + " Lightning power strikes!");

        // Create and execute an explosion attack
        this.explosion = new Explosion(EXPLOSION_DAMAGE);
        explosion.execute(enemy, map);

        // Check if the target is on an electric-weak ground
        if (target.isConscious() && map.locationOf(target).getGround().hasCapability(Ability.WEAKNESS_ELECTRIC)) {
            display.println(target + " is standing on top of a body of water, the damage of the special attack is doubled!");
            explosion.execute(enemy, map);  // Perform a second explosion
        }
    }

    @Override
    public Ability getNextPower() {
        double chance = Math.random(); // Generate a random number between 0 and 1
        Display display = new Display();
        if (chance < FROST_DIVINE_POWER_CHANCE) { // 40% chance to transition to Frost Divine Power
            display.println("As the air grows cold, Divine Power Swapped to FROST");
            return Ability.FROST_DIVINE_POWER;
        } else if (chance < (FROST_DIVINE_POWER_CHANCE + WIND_DIVINE_POWER_CHANCE)) { // 40% chance to transition to Wind Divine Power
            display.println("With a gust of grace, Divine Power Swapped to WIND");
            return Ability.WIND_DIVINE_POWER;
        } else { // 20% chance to stay in Lightning Divine Power
            display.println("A thunderous roar echoes, Divine Power Maintained LIGHTNING");
            return Ability.LIGHTNING_DIVINE_POWER;
        }
    }
}
