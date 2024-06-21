package com.project.paradoxplatformer.utils.world.interpolations;

import com.project.paradoxplatformer.utils.world.vector.api.Vector2D;

public interface InterpolatorFactory {
    
    Interpolator<Vector2D> linear();

    Interpolator<Vector2D> easeIn();

    Interpolator<Vector2D> easeOut();

    Interpolator<Vector2D> easeInOut();
}
