package com.project.paradoxplatformer.model.entity.dynamics;

/**
 * Represents an object that can move vertically.
 */
public interface VerticalObject {

    /**
     * Makes the object jump.
     */
    void jump();

    /**
     * Makes the object fall.
     */
    void fall();
}
