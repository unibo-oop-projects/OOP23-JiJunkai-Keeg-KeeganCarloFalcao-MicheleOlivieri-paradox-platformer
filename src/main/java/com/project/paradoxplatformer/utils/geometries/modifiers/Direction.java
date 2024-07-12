package com.project.paradoxplatformer.utils.geometries.modifiers;



public enum Direction {
    RIGHT(false),
    LEFT(false);

    private boolean activatedStatus;

    private Direction(final boolean active) {
        this.activatedStatus = active;
    }

    public boolean getStatus() {
        return this.activatedStatus;
    }

    public void setStatus(final boolean newStatus) {
        this.activatedStatus = newStatus;
    }

    public Direction opposite() {
        return this == Direction.LEFT ? Direction.RIGHT : Direction.LEFT;
    }
}

