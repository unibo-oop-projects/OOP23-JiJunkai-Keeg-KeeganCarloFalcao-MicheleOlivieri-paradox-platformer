package com.project.paradoxplatformer.controller.deserialization.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object (DTO) for sprite information.
 * <p>
 * This class represents the data structure for sprite attributes used in
 * deserialization. It contains information about the sprite's animation
 * frames and whether the sprite is special. The attributes are used to
 * configure sprite animations for different states like idle, running,
 * jumping, and falling.
 * </p>
 */
public final class SpriteDTO {

    /**
     * Indicates if the sprite is special.
     */
    @JsonProperty
    private final boolean special;

    /**
     * The number of frames for the idle animation.
     */
    @JsonProperty
    private final int idleFrames;

    /**
     * The number of frames for the running animation.
     */
    @JsonProperty
    private final int runningFrames;

    /**
     * The number of frames for the jumping animation.
     */
    @JsonProperty
    private final int jumpingFrames;

    /**
     * The number of frames for the falling animation.
     */
    @JsonProperty
    private final int fallingFrames;

    /**
     * The minimum number of frames required for the sprite.
     */
    @JsonProperty
    private final int minFrames;

    /**
     * Default constructor initializing all fields to default values.
     * <p>
     * Sets {@code special} to {@code false}, and all frame counts to {@code 0}.
     * </p>
     */
    public SpriteDTO() {
        this.special = false;
        this.idleFrames = 0;
        this.jumpingFrames = 0;
        this.runningFrames = 0;
        this.fallingFrames = 0;
        this.minFrames = 0;
    }

    /**
     * Gets whether the sprite is special.
     *
     * @return {@code true} if the sprite is special, {@code false} otherwise
     */
    public boolean isSpecial() {
        return this.special;
    }

    /**
     * Gets the number of frames for the idle animation.
     *
     * @return the number of idle animation frames
     */
    public int getIdleFrames() {
        return this.idleFrames;
    }

    /**
     * Gets the number of frames for the running animation.
     *
     * @return the number of running animation frames
     */
    public int getRunningFrames() {
        return this.runningFrames;
    }

    /**
     * Gets the number of frames for the jumping animation.
     *
     * @return the number of jumping animation frames
     */
    public int getJumpingFrames() {
        return this.jumpingFrames;
    }

    /**
     * Gets the number of frames for the falling animation.
     *
     * @return the number of falling animation frames
     */
    public int getFallingFrames() {
        return this.fallingFrames;
    }

    /**
     * Gets the minimum number of frames required for the sprite.
     *
     * @return the minimum number of frames
     */
    public int getMinFrames() {
        return this.minFrames;
    }
}
