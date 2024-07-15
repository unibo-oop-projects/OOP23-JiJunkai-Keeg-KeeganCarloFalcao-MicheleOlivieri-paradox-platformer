package com.project.paradoxplatformer.controller.deserialization.dtos;

public final class TrajMacro {
    
    private final int x;
    private final int y;
    private final long duration;
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
