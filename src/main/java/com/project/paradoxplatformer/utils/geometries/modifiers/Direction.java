package com.project.paradoxplatformer.utils.geometries.modifiers;

/**
 * Enum representing possible directions with a status of whether they are
 * activated or not.
 */
public enum Direction {
    /**
     * Direction to the right.
     */
    RIGHT(false),

    /**
     * Direction to the left.
     */
    LEFT(false);

    private boolean activatedStatus;

    /**
     * Constructs a {@code Direction} with the specified activation status.
     *
     * @param active the initial activation status of this direction
     */
    Direction(final boolean active) {
        this.activatedStatus = active;
    }

    /**
     * Gets the activation status of this direction.
     *
     * @return {@code true} if the direction is activated, {@code false} otherwise
     */
    public boolean getStatus() {
        return this.activatedStatus;
    }

    /**
     * Sets the activation status of this direction.
     *
     * @param newStatus the new activation status to set
     */
    public void setStatus(final boolean newStatus) {
        this.activatedStatus = newStatus;
    }

    /**
     * Returns the opposite direction.
     *
     * @return the opposite direction of the current one
     */
    public Direction opposite() {
        return this == Direction.LEFT ? Direction.RIGHT : Direction.LEFT;
    }
}
