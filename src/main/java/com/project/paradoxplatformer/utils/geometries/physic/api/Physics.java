package com.project.paradoxplatformer.utils.geometries.physic.api;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.utils.geometries.interpolations.Interpolator;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * Represents an interface for managing vectors 2D dimensional movements. (e.g from A to B)
 */
public interface Physics {
    /**
     * Manages to perform a fully interpolated discrete values of a straight up movement 
     * from start to end based on duration.
     * @param start initial vector
     * @param end destination endpoint vector
     * @param duration duration of the movement
     * @param interpType interpolation type
     * @param dt delta time
     * @return a pair of the resulting vector at a particular delta time and the percentage 
     * indicating whether the path has been completed.
     */
    Pair<Vector2D, Double> moveTo(
        Vector2D start, 
        Vector2D end,
        long duration,
        Interpolator<Vector2D> interpType,
        long dt
    );

    /**
     * Manages to perform a step to step path progression. However, unlike 
     * {@link #moveTo(Vector2D, Vector2D, long, Interpolator, long)} it's not 
     * based upon duration, so it is not a straight-foward operation.
     * @param start initial vector
     * @param end destination endpoint vector
     * @param interpType interpolation type
     * @param dt delta time
     * @return a pair of the resulting vector at a particular delta time.
     */
    Vector2D step(
        Vector2D start,
        Vector2D end,
        Interpolator<Vector2D> interpType,
        long dt
    );

    /**
     * Implemented are fields are reseted.
     * @return the current vector of the delta time session.
     */
    Vector2D stop();
}
