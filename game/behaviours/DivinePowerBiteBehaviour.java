package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.divinepower.DivinePower;
import game.divinepower.FrostDivinePower;
import game.divinepower.LightningDivinePower;
import game.divinepower.WindDivinePower;
import game.attributes.Ability;

import java.util.Random;

/**
 * Behaviour representing a divine bite attack with a chance to swap divine powers.
 * The DivineBeastDancingLion may perform a bite attack with damage or switch to a different divine power.
 * @author Aw Shen Yang
 */
public class DivinePowerBiteBehaviour extends Action implements Behaviour {

    private final Actor TARGET; // The actor being targeted for the bite
    private final int BITE_CHANCE = 30; // 30% chance of successful bite
    private final int BITE_DAMAGE = 150; // Damage dealt by a successful bite
    private final int DIVINE_POWER_SWAP_CHANCE = 25; // 25% chance of swapping divine power

    private DivinePower currentDivinePower; // Current divine power being used
    private Ability currentDivinePowerAbility;// Current divine power ability being used

    /**
     * Constructor for DivinePowerBiteBehaviour
     * @param target the actor being targeted
     */
    public DivinePowerBiteBehaviour(Actor target) {
        this.TARGET = target;
    }

    /**
     * Executes the divine bite attack with a chance to switch divine powers.
     * @param enemy the attacking actor (Divine Beast)
     * @param map the GameMap the actors are on
     * @return a description of the action's result
     */
    @Override
    public String execute(Actor enemy, GameMap map) {
        String ret = "";

        // Handle the divine power action and possibly switch powers
        ret += executeDivinePowerAction(enemy);
        // Perform special attack with the current divine power
        currentDivinePower.performSpecialAttack(enemy, TARGET, map);
        // Check whether TARGET survived the Special Attack
        if (TARGET.isConscious()){
            // Handle the bite action
            ret += attemptBite(enemy, map);
        }

        return ret;
    }

    /**
     * Handles the divine power swap and special attack action.
     */
    private String executeDivinePowerAction(Actor enemy) {
        String ret = "";
        Random random = new Random();

        // Check if the enemy has divine power and should potentially swap
        if (enemy.hasCapability(Ability.DIVINE_POWER)) {
            if (random.nextInt(100) < DIVINE_POWER_SWAP_CHANCE) {
                // Attempt to swap divine powers if the chance condition is met
                enemy.removeCapability(getCurrentDivinePowerCapability(enemy));
                currentDivinePowerAbility = currentDivinePower.getNextPower();
                if (currentDivinePowerAbility == Ability.WIND_DIVINE_POWER){
                    currentDivinePower = new WindDivinePower();
                } else if (currentDivinePowerAbility == Ability.FROST_DIVINE_POWER) {
                    currentDivinePower = new FrostDivinePower();
                } else if (currentDivinePowerAbility == Ability.LIGHTNING_DIVINE_POWER) {
                    currentDivinePower = new LightningDivinePower();
                }
                enemy.addCapability(currentDivinePowerAbility);
                ret += enemy + " now performs a special attack\n";
            } else {
                getCurrentDivinePowerCapability(enemy);
                ret += enemy + " keeps current divine power!\n";
                ret += enemy + " now performs a special attack\n";
            }
        }
        return ret;
    }

    /**
     * Handles the bite action of the enemy against the target.
     */
    private String attemptBite(Actor enemy, GameMap map) {
        String ret = "";
        Random random = new Random();

        // Attempt the bite action
        ret += enemy + " attempts to bite " + TARGET + "\n";
        if (random.nextInt(100) < BITE_CHANCE) {
            TARGET.hurt(BITE_DAMAGE);
            ret += enemy + " successfully bites " + TARGET + " causing " + BITE_DAMAGE + " damage.";

            // Check if the target becomes unconscious after the bite
            if (!TARGET.isConscious()) {
                ret += '\n' + TARGET.unconscious(enemy, map);
            }
        } else {
            ret += enemy + " misses " + TARGET;
        }
        return ret;
    }

    /**
     * Helper method to get the current divine power capability of the enemy.
     */
    private Ability getCurrentDivinePowerCapability(Actor enemy) {
        if (enemy.hasCapability(Ability.WIND_DIVINE_POWER)) {
            currentDivinePower = new WindDivinePower();
            return Ability.WIND_DIVINE_POWER;
        } else if (enemy.hasCapability(Ability.FROST_DIVINE_POWER)) {
            currentDivinePower = new FrostDivinePower();
            return Ability.FROST_DIVINE_POWER;
        } else if (enemy.hasCapability(Ability.LIGHTNING_DIVINE_POWER)) {
            currentDivinePower = new LightningDivinePower();
            return Ability.LIGHTNING_DIVINE_POWER;
        }
        return null;
    }


    @Override
    public String menuDescription(Actor actor) {
        return "";
    }

    /**
     * Determines if the Divine Power Bite Behaviour should be performed.
     * @param actor The actor performing the action
     * @param map The game map containing the actor and the target
     * @return The action if the target is within the actor's surroundings else null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination() == map.locationOf(TARGET)) {
                return this;
            }
        }
        return null;
    }
}
