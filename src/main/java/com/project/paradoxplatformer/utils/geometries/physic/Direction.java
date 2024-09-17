package com.project.paradoxplatformer.utils.geometries.physic;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Enum representing possible directions with a status of whether they are
 * activated or not.
 */
@SuppressFBWarnings(value = "MS_EXPOSE_REP", justification = "The Direction.values() method is used to provide a predefined set of constants and is necessary for enum functionality.")
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
        if (this.activatedStatus != newStatus) {
            this.activatedStatus = newStatus;
        }
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
