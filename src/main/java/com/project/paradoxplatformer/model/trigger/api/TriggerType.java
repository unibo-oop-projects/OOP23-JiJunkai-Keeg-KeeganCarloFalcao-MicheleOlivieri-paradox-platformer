package com.project.paradoxplatformer.model.trigger.api;

/**
 * Enum representing different types of triggers in the game.
 * Each trigger type serves a specific purpose and interacts differently within
 * the game environment.
 */
public enum TriggerType {
    /**
     * Represents a floor trigger, which might be used to detect when the player is
     * on the ground.
     */
    FLOOR,

    /**
     * Represents a button trigger, which might be used for interactive elements
     * that can be pressed or activated.
     */
    BUTTON,

    /**
     * Represents a warp trigger, which might be used to transport or teleport the
     * player to a different location.
     */
    WARP,
}
