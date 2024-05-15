package com.project.paradoxplatformer.model;

public class StaticObstacle extends Obstacle {
    public StaticObstacle(double x, double y) {
        super(x, y);
    }

    @Override
    public void update(double deltaTime) {
        // Static obstacles don't need to update their state
    }
}
