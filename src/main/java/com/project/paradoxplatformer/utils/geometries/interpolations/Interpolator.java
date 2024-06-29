package com.project.paradoxplatformer.utils.geometries.interpolations;

public interface Interpolator<V> {
     V lerp(V start, V end, double t);
}
