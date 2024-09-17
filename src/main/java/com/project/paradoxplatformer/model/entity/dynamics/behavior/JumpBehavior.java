package com.project.paradoxplatformer.model.entity.dynamics.behavior;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * Defines the behavior for jumping, including methods to manage gravity and jumping logic.
 * Implementations could follow different strategies such as platform or flappy bird styles.
 */
public interface JumpBehavior {

    /**
     * Performs a jump action and returns the resulting velocity vector.
     * @return an Optional containing the velocity vector if the jump is performed, otherwise empty.
     */
    Optional<Vector2D> jump();

    /**
     * Calculates the falling movement based on current gravity.
     * @return the velocity vector for falling.
     */
    Vector2D fall();

    /**
     * Sets the state of falling for the object.
     * @param falling true if the object is falling, false otherwise.
     */
    void setFalling(boolean falling);

    /**
     * Checks if the object is currently falling.
     * @return true if the object is falling, false otherwise.
     */
    boolean isFalling();

    /**
     * Resets the gravity to its initial state.
     */
    void resetGravity();
}
