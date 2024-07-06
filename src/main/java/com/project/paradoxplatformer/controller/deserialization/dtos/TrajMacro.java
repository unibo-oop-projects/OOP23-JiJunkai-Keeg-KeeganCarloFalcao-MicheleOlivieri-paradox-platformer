package com.project.paradoxplatformer.controller.deserialization.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrajMacro {
    @JsonProperty
    private int x;

    @JsonProperty
    private int y;

    @JsonProperty
    private long duration;

    @JsonProperty
    private String vector;

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
