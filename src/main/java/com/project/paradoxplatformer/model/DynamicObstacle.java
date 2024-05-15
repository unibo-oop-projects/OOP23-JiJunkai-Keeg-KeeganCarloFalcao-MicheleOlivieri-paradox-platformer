package com.project.paradoxplatformer.model;

public class DynamicObstacle extends Obstacle {
    private double velocityX, velocityY;

    public DynamicObstacle(double x, double y, double velocityX, double velocityY) {
        super(x, y);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    @Override
    public void update(double deltaTime) {
        x += velocityX * deltaTime;
        y += velocityY * deltaTime;
        // Implement any additional behavior, such as bouncing off walls
    }
}
