package com.project.paradoxplatformer.utils.world.modifiers;



public enum MovingType {
    RIGHT(false),
    LEFT(false);

    private boolean activatedStatus;

    private MovingType(boolean active) {
        this.activatedStatus = active;
    }

    public boolean getStatus() {
        return this.activatedStatus;
    }

    public void setStatus(boolean newStatus) {
        this.activatedStatus = newStatus;
    }

    public MovingType opposite() {
        return this == MovingType.LEFT ? MovingType.RIGHT : MovingType.LEFT;
    }
}

