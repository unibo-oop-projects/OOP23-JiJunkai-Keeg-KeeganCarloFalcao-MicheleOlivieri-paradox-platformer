package com.project.paradoxplatformer.utils.geometries.modifiers.api;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.utils.geometries.interpolations.Interpolator;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * 
 */
public interface Physics {
    /**
     * 
     * @param start
     * @param end
     * @param duration
     * @param interpType
     * @param dt
     * @return
     */
    Pair<Vector2D, Double> moveTo(final Vector2D start, final Vector2D end, final long duration, Interpolator<Vector2D> interpType, final long dt);
    /**
     * 
     * @param start
     * @param end
     * @param interpType
     * @param dt
     * @return
     */
    Vector2D step(final Vector2D start, final Vector2D end, final Interpolator<Vector2D> interpType, final long dt);
    /**
     * 
     * @return
     */
    Vector2D stop();
}
