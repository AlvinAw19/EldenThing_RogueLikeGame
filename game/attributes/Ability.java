package game.attributes;

/**
 * Enum representing different abilities that actors or objects in the game may have.
 * @author Aw Shen Yang, Au Jenq, Somkiet Phromsuwan
 */
public enum Ability {
    /**
     * Allows actors to enter the Floor ground.
     */
    CAN_ENTER_FLOOR,

    /**
     * Allows actors to use and pick up weapon items.
     */
    CAN_USE_WEAPON,

    /**
     * Indicates that an actor or ground is fire-resistant.
     */
    FIRE_RESISTANT,

    /**
     * Indicates that an actor is poison-resistant.
     */
    POISON_RESISTANT,

    /**
     * Grants immunity to certain attacks or effects.
     */
    IMMUNE,

    /**
     * Grants the actor Divine Power.
     */
    DIVINE_POWER,

    /**
     * Grants the actor Wind Divine Power.
     */
    WIND_DIVINE_POWER,

    /**
     * Grants the actor Frost Divine Power.
     */
    FROST_DIVINE_POWER,

    /**
     * Grants the actor Lightning Divine Power.
     */
    LIGHTNING_DIVINE_POWER,

    /**
     * Indicate vulnerable to electric-based attacks.
     */
    WEAKNESS_ELECTRIC,

    /**
     * Indicates that an actor or object is slippery (e.g., on wet surfaces).
     */
    SLIPPERY
}
