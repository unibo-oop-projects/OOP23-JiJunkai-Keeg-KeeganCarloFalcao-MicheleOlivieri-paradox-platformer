package com.monkeytd.model;

public interface Interpolator<V> {
     V lerp(V start, V end, double t);
}
