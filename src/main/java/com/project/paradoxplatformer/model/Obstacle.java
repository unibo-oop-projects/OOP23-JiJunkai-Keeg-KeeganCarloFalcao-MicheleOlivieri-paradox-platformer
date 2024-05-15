package com.project.paradoxplatformer.model;

public abstract class Obstacle {
    protected double x, y;

    public Obstacle(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update(double deltaTime);

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
