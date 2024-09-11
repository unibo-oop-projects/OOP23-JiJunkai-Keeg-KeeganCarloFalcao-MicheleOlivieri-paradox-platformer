package com.project.paradoxplatformer.controller.deserialization.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public SpriteDTO() {
        this.special = false;
        this.idleFrames = 0;
        this.jumpingFrames = 0;
        this.runningFrames = 0;
        this.fallingFrames = 0;
        this.minFrames = 0;
    }

    public boolean isSpecial() {
        return this.special;
    }

    public int getIdleFrames() {
        return this.idleFrames;
    }

    public int getRunningFrames() {
        return this.runningFrames;
    }

    public int getJumpingFrames() {
        return this.jumpingFrames;
    }

    public int getFallingFrames() {
        return this.fallingFrames;
    }

    public int getMinFrames() {
        return this.minFrames;
    }
}
