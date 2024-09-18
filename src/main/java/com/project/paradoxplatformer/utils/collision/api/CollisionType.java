package com.project.paradoxplatformer.utils.collision.api;

/**
 * Enum representing different types of collisions that can occur in the game.
 */
public enum CollisionType {
    /**
     * Represents a platform collision type.
     * Typically used for platforms that players can stand on.
     */
    PLATFORM,

    /**
     * Represents a visible trigger collision type.
     * Used for objects that trigger events or actions when the player interacts
     * with them.
     */
    BUTTON,

    /**
     * Represents an invisible trigger collision type.
     * Used for objects that trigger events or actions when the player interacts
     * with them.
     */
    FLOOR,

    /**
     * Represents the player collision type.
     */
    PLAYER,

    /**
     * Represents a death obstacle collision type.
     * Used for objects or areas that cause the player to lose the game or respawn.
     */
    DEATH_OBS,

    /**
     * Represents a springs collision type.
     * Used for objects that interact with the player by bouncing
     * movement in a spring-like manner.
     */
    SPRINGS,

    /**
     * Represents a collecting collision type.
     * Used for objects that the player can collect or pick up.
     */
    COLLECTING,

    /**
     * Represents a walls collision type.
     * Used for walls or barriers that block the player's movement.
     */
    WALLS,

    /**
     * Represents a warp collision type.
     * Used for objects that transport the player to somewhere else.
     */
    WARP,

    /**
     * Represents a saw collision type.
     * Used for saw-like obstacles that can cause damage to the player.
     */
    SAW
}
