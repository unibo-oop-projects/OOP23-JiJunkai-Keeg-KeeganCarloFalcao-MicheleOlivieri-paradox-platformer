package com.project.paradoxplatformer.utils.geometries.physic;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.utils.geometries.interpolations.Interpolator;
import com.project.paradoxplatformer.utils.geometries.physic.api.Physics;
import com.project.paradoxplatformer.utils.geometries.vector.api.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * A basic implmentation of the physics engine.
 * @see Physics
 */
public final class PhysicsEngine implements Physics {

    private static final double CLAMP_VALUE = 1.;
    private static final double MILLISECOND_IN_SEC = 1000.d;
    private double elapseTime;

    /**
     * A non-argument constructor to initialize the engine.
     */
    public PhysicsEngine() {
        this.elapseTime = 0.;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Vector2D, Double> moveTo(
            final Vector2D start,
            final Vector2D end, 
            final long duration, 
            final Interpolator<Vector2D> interpType,
            final long dt
        ) {
        this.elapseTime += dt;
        final double y = elapseTime / (duration * MILLISECOND_IN_SEC);

        final double percentage = Math.min(y, CLAMP_VALUE);
        return Pair.of(interpType.lerp(start, end, percentage), percentage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D step(
        final Vector2D start, 
        final Vector2D end, 
        final Interpolator<Vector2D> interpType, 
        final long dt
        ) {
        return interpType.lerp(start, end, dt / MILLISECOND_IN_SEC);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D stop() {
        this.elapseTime = 0.d;
        return Polar2DVector.nullVector();
    }
}
