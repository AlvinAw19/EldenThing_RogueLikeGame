package game.weapons.weaponart;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for weapon arts to be attached to a weapon
 * Created by:
 * @author Asyraaf Rahman
 */
public interface WeaponArt {
    /**
     * Method for executing the specific effect of a WeaponArt
     *
     * @param attacker The actor using the weapon.
     * @param target The actor being attacked.
     * @param map The game map where the action is taking place.
     * @return A string description of the weapon art action.
     * @author Asyraaf Rahman
     */
    String use(Actor attacker, Actor target, GameMap map);


    /**
     * Returns the mana cost of the weapon art.
     */
    int manaCost();
}
