package com.project.paradoxplatformer.utils.geometries.interpolations;

import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * 
 */
public interface InterpolatorFactory {
    /**
     * 
     * @return
     */ 
    Interpolator<Vector2D> linear();

    /**
     * 
     */
    Interpolator<Vector2D> easeIn();

    /**
     * 
     * @return
     */
    Interpolator<Vector2D> easeOut();

    /**
     * 
     * @return
     */
    Interpolator<Vector2D> easeInOut();
}
