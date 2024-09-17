package com.project.paradoxplatformer.model.obstacles;

/**
 * Enum representing different types of obstacles in the game.
 * Each obstacle type serves a specific purpose and interacts differently within
 * the game environment.
 */
public enum ObstacleType {
    /**
     * Represents a platform collision type.
     * Typically used for platforms that players can stand on.
     */
    PLATFORM,

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
    WARP
}
