package com.project.paradoxplatformer.utils.world.modifiers.api;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.utils.world.interpolations.Interpolator;
import com.project.paradoxplatformer.utils.world.vector.api.Vector2D;

public interface Modifier {

    public Pair<Vector2D, Double> moveTo(Vector2D start, Vector2D end, long duration, Interpolator<Vector2D> interpType, long dt);

    public Vector2D step(Vector2D start, Vector2D end, Interpolator<Vector2D> interpType, long dt);

    public Vector2D stop();
}
