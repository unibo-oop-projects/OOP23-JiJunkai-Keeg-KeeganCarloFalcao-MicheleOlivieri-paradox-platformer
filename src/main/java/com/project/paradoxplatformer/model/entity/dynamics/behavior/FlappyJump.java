package com.project.paradoxplatformer.model.entity.dynamics.behavior;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * This class implements a flappy jump behavior where the player experiences
 * a strong upward force and then gradually falls down with increasing speed.
 */
public final class FlappyJump extends AbstractJumpBehavior {

    private static final double POWER = 3;
    private static final double ANTI_GRAVITY = -POWER + 1;
    private static final double GRAVITY_DECREASE = 0.25; // Magic number replaced with constant
    private double grav = ANTI_GRAVITY;

    @Override
    public Optional<Vector2D> jump() {
        this.grav = POWER;
        return Optional.of(new Simple2DVector(0., POWER));
    }

    @Override
    public Vector2D fall() {
        // Avoid inner assignments and ensure proper spacing
        this.grav -= GRAVITY_DECREASE;
        return new Simple2DVector(0., this.grav);
    }

    @Override
    public void resetGravity() {
        this.grav = POWER;
    }
}
