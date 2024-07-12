package com.project.paradoxplatformer.controller.deserialization.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class TrajMacro {
    @JsonProperty
    private final int x;

    @JsonProperty
    private final int y;

    @JsonProperty
    private final long duration;

    @JsonProperty
    private final String vector;

    private TrajMacro() {
        this.x = 0;
        this.y = 0;
        this.duration = 0;
        this.vector = "";
    }

    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public long getDuration() {
        return duration;
    }
    
    public String getVector() {
        return vector;
    }
    
}
