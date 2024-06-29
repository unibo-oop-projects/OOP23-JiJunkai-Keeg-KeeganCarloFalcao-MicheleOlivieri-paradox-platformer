package com.project.paradoxplatformer.utils.geometries.interpolations;

import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public interface InterpolatorFactory {
    
    Interpolator<Vector2D> linear();

    Interpolator<Vector2D> easeIn();

    Interpolator<Vector2D> easeOut();

    Interpolator<Vector2D> easeInOut();
}
