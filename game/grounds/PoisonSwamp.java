package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.statusEffects.PoisonEffect;
import game.attributes.Ability;

/**
 * The PoisonSwamp class represents a ground type that inflicts poison damage on actors.
 * It extends the Ground class and applies a poison effect to actors that are not poison-resistant.
 * @author Au Jenq
 */
public class PoisonSwamp extends Ground {
    private final int POISON_DAMAGE = 5;
    private final int POISON_DURATION = 3;
    public PoisonSwamp() {
        super('+', "Poison Swamp");
    }


    /**
     * Called once per turn to update the state of the ground.
     * Applies a poison effect to any actor present at the location if they are not poison-resistant.
     *
     * @param location The location of the ground.
     */
    @Override
    public void tick(Location location) {

        if ((location.containsAnActor()) && (!(location.getActor().hasCapability(Ability.POISON_RESISTANT)))){
            Actor actor = location.getActor();
            actor.addStatusEffect(new PoisonEffect(POISON_DAMAGE,POISON_DURATION, null));
        }
    }

}
