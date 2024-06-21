package com.project.paradoxplatformer.utils.world.interpolations;

public interface Interpolator<V> {
     V lerp(V start, V end, double t);
}
