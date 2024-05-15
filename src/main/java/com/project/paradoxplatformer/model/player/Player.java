package com.project.paradoxplatformer.model.player;

public class Player {
    private static final double GRAVITY = 1000; // Gravity constant
    private static final double JUMP_VELOCITY = -500; // Initial velocity when jumping
    public static final double GROUND_LEVEL = 500; // Ground level position

    private double x, y;
    private double velocityX, velocityY;
    private boolean isJumping;

    public Player() {
        x = 0;
        y = GROUND_LEVEL;
        velocityX = 0;
        velocityY = 0;
        isJumping = false;
    }

    public void update(double deltaTime) {
        // Apply gravity
        velocityY += GRAVITY * deltaTime;

        // Update position
        x += velocityX * deltaTime;
        y += velocityY * deltaTime;

        // Check if the player is on the ground
        if (y >= GROUND_LEVEL) {
            y = GROUND_LEVEL; // Ensure the player stays on the ground
            velocityY = 0; // Stop vertical movement
            isJumping = false; // Reset jump state
        }
    }

    public void jump() {
        if (!isJumping) {
            velocityY = JUMP_VELOCITY;
            isJumping = true;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVelocityX() { // Getter for velocityX
        return velocityX;
    }

    public double getVelocityY() { // Getter for velocityY
        return velocityY;
    }

    public void setVelocity(double velocityX, double velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public boolean isJumping() {
        return isJumping;
    }
}
