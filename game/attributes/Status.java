package game.attributes;

/**
 * Use this enum class to represent a status.
 * Example #1: if the player is sleeping, you can attack a Status.SLEEP to the player class
 * Created by:
 * @author Riordan D. Alfredo
 */
public enum Status {
    /**
     * Indicates that an actor is hostile to enemy
     */
    HOSTILE_TO_ENEMY,

    /**
     * Indicates that Pet is already chosen by Player
     */
    CHOSEN_PET,

    /**
     * Indicates that Player already has a Pet
     */
    HAS_PET
}
