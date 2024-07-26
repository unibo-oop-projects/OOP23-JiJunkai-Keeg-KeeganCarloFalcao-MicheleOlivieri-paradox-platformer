package com.project.paradoxplatformer.utils.geometries.interpolations;

/**
 * 
 */
public interface Interpolator<V> {
     /**
      * 
      * @param start
      * @param end
      * @param t
      * @return
      */
     V lerp(V start, V end, double t);
}
