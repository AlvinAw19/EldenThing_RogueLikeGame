package game.grounds;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.attributes.Ability;

/**
 * Represents a Fire item that inflicts burn damage to actors within its vicinity.
 * The fire will last for a specified duration and will damage any actor that is not fire-resistant.
 * Created by: Somkiet Phromsuwan
 * @author Somkiet Phromsuwan
 */
public class Fire extends Item{

    private final int BURN_DAMAGE = 5;
    private int turnsOnFireRemaining = 5;
    private final int BURN_DURATION = 5;
    private Actor enemy;

    /**
     * Constructs a Fire instance.
     *
     * @param enemy the Actor that cause the fire.
     */
    public Fire(Actor enemy) {
        super("Fire",'w',false);
        this.enemy = enemy;
    }


    /**
     * Executes the fire's effect every game tick.
     * If there are remaining turns for the fire, it checks for actors in the location and applies burn damage
     * to any actor that is not fire-resistant. If an actor is rendered unconscious, a message is displayed.
     * If the fire has reached the end of its duration, it is removed from the location.
     *
     * @param location the Location where the fire is located
     */
    @Override
    public void tick(Location location) {
        Display display = new Display();

        if (turnsOnFireRemaining < BURN_DURATION && turnsOnFireRemaining > 0 && location.containsAnActor()) {
            Actor actor = location.getActor();
            if (!actor.hasCapability(Ability.FIRE_RESISTANT)) {
                actor.hurt(BURN_DAMAGE);
                display.println("Fire burns " + actor);
                if (!(actor.isConscious())) {
                    display.println(actor.unconscious(enemy, location.map()));
                }
            }
        }
        if (turnsOnFireRemaining <= 0){
            location.removeItem(this);
        }

        turnsOnFireRemaining--;
    }
}
