package com.project.paradoxplatformer.view.graphics.sprites;

/**
 * Enum representing the various statuses or states that a sprite can be in.
 * These statuses are used to determine which set of images or animations should
 * be displayed for a sprite.
 */
public enum SpriteStatus {
    /**
     * Represents the running state of the sprite.
     * Typically used when the sprite is in motion.
     */
    RUNNING,

    /**
     * Represents the jumping state of the sprite.
     * Used when the sprite is airborne, typically during a jump action.
     */
    JUMPING,

    /**
     * Represents the idle state of the sprite.
     * Used when the sprite is not performing any actions and is stationary.
     */
    IDLE,

    /**
     * Represents the falling state of the sprite.
     * Used when the sprite is descending or falling through the air.
     */
    FALLING
}
