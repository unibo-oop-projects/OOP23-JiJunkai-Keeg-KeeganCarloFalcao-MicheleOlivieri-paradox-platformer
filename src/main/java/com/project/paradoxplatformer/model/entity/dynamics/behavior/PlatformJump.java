package com.project.paradoxplatformer.model.entity.dynamics.behavior;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * Defines the behavior for platform jumping, including gravity and falling
 * logic.
 */
public final class PlatformJump extends AbstractJumpBehavior {

    private static final double POWER = 13;
    private static final double ANTI_GRAVITY = -POWER + 1;
    private double grav = ANTI_GRAVITY;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Vector2D> jump() {
        if (grav == ANTI_GRAVITY) {
            // Sets the gravity to the jump power and resets the falling state
            grav = POWER;
            this.setFalling(true);
            return Optional.of(new Simple2DVector(0., POWER));
        } else {
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D fall() {
        if (this.isFalling()) {
            this.grav -= 1;
            return new Simple2DVector(0., this.grav);
        } else {
            // If the player is not falling, return a zero vector to stop vertical movement
            return new Simple2DVector(0., 0.);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetGravity() {
        this.grav = ANTI_GRAVITY;
    }
}
