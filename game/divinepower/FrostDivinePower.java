package game.divinepower;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Display;
import game.attributes.Ability;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Frost Divine Power which forces the target to drop all items if they are on water.
 * @author Aw Shen Yang
 */
public class FrostDivinePower implements DivinePower {

    @Override
    public void performSpecialAttack(Actor enemy, Actor target, GameMap map) {
        Display display = new Display();
        // Check if the target is standing on water (slippery ground)
        if (map.locationOf(target).getGround().hasCapability(Ability.SLIPPERY)) {
            display.println(target + " is standing on top of a body of water, "+ target +" slipped and all of their inventory items drop!");

            // Collect items to be dropped
            List<Item> itemsToDrop = new ArrayList<>();
            for (Item item : target.getItemInventory()) {
                DropAction dropAction = item.getDropAction(target);
                if (dropAction != null) {
                    itemsToDrop.add(item);
                }
            }

            // Drop the items
            for (Item item : itemsToDrop) {
                DropAction dropAction = item.getDropAction(target);
                if (dropAction != null) {
                    dropAction.execute(target, map);
                }
            }
        } else {
            display.println("The Frost power had no effect as " + target + " is not standing on top of a body of water!");
        }
    }

    @Override
    public Ability getNextPower() {
        Display display = new Display();
        display.println("With a gust of grace, Divine Power Swapped to WIND");
        // Transition to Wind Divine Power
        return Ability.WIND_DIVINE_POWER;
    }
}
