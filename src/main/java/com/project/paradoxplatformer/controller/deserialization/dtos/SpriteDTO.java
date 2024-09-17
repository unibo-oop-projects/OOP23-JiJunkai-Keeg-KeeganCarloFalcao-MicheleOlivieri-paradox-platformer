package com.project.paradoxplatformer.controller.deserialization.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the sprite DTO for sprite images.
 */
public final class SpriteDTO {
    
    @JsonProperty
    private final boolean special;
    @JsonProperty
    private final int idleFrames;
    @JsonProperty
    private final int runningFrames;
    @JsonProperty
    private final int jumpingFrames;
    @JsonProperty
    private final int fallingFrames;
    @JsonProperty
    private final int minFrames;

    /**
     * Non argument constructor for a basic sprite image (not special).
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
     * Check flag wether a sprite is full sprite player (so it can run, jump...) or a looping image so it is always on idle.
     * @return flag to distinguish wether is a player or a normal sprite
     */
    public boolean isSpecial() {
        return this.special;
    }

    /**
     * Number of sprite images regarding the idle animation.
     * @return idle frames
     */
    public int getIdleFrames() {
        return this.idleFrames;
    }

    /**
     * Number of sprite images regarding the running animation.
     * @return running frames
     */
    public int getRunningFrames() {
        return this.runningFrames;
    }

    /**
     * Number of sprite images regarding the running animation
     * @return running frames
     */
    public int getJumpingFrames() {
        return this.jumpingFrames;
    }

    /**
     * Number of sprite images regarding the running animation
     * @return running frames
     */
    public int getFallingFrames() {
        return this.fallingFrames;
    }

    /**
     * Has a value greater than zero only if the image is a sprite.
     * It basically provides the minimum number of frames nedded to change the current sprite. It is useful to give a sense of
     * animation. Note that its frame management is different than the delta time and gameloop cause they are view-dedicated sections.
     * @return the minimum amount of frames for swithichng to the next image
     */

    public int getMinFrames() {
        return this.minFrames;
    }
}
