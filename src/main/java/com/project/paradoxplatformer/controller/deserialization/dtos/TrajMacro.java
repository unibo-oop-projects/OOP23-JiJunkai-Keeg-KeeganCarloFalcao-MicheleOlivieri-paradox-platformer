package com.project.paradoxplatformer.controller.deserialization.dtos;

public class TrajMacro {
    private int x;
    private int y;
    private long duration;
    private String vector;

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public long getDuration() {
        return duration;
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }
    public String getVector() {
        return vector;
    }
    public void setVector(String vector) {
        this.vector = vector;
    }
}
