package game.divinepower;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.displays.Display;
import game.attributes.Ability;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the Wind Divine Power which can move the target to a random adjacent location.
 * @author Aw Shen Yang
 */
public class WindDivinePower implements DivinePower {
    private final int FROST_DIVINE_POWER_CHANCE = 30;

    @Override
    public void performSpecialAttack(Actor enemy, Actor target, GameMap map) {
        Display display = new Display();
        display.println("The " + enemy + " Wind power moves the target!");

        Location currentLocation = map.locationOf(enemy);
        List<Exit> adjacentExits = new ArrayList<>(currentLocation.getExits());

        Random rand = new Random();
        Exit exit = adjacentExits.get(rand.nextInt(adjacentExits.size()));
        Location newLocation = exit.getDestination();

        // Check if the new location is valid for the target to move to
        if ((!newLocation.containsAnActor()) && (newLocation.getGround().canActorEnter(target))) {
            map.moveActor(target, newLocation);
            display.println(target + " blows to " + newLocation);
        } else {
            display.println("Something is blocking the way of " + target);
        }
    }

    @Override
    public Ability getNextPower() {
        Random random = new Random();
        int chance = random.nextInt(100); // Generate a random number between 0 and 99
        Display display = new Display();

        if (chance < FROST_DIVINE_POWER_CHANCE) { // 30% chance to transition to Frost Divine Power
            display.println("As the air grows cold, Divine Power Swapped to FROST");
            return Ability.FROST_DIVINE_POWER;
        } else { // 70% chance to transition to Lightning Divine Power
            display.println("A thunderous roar echoes, Divine Power Swapped to LIGHTNING");
            return Ability.LIGHTNING_DIVINE_POWER;
        }
    }
}
