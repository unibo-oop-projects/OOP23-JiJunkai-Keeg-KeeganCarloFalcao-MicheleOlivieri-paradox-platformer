package com.project.paradoxplatformer.utils.geometries.physic;

/**
 * Enum representing possible directions with a status of whether they are
 * activated or not.
 */
public enum Direction {
    /**
     * Direction to the right.
     */
    RIGHT,

    /**
     * Direction to the left.
     */
    LEFT;

    /**
     * Returns the opposite direction.
     *
     * @return the opposite direction of the current one
     */
    public Direction opposite() {
        return this == LEFT ? RIGHT : LEFT;
    }
}
