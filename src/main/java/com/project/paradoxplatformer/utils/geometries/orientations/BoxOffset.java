package com.project.paradoxplatformer.utils.geometries.orientations;

/**
 * Evaluates an offset
 */
@FunctionalInterface
public interface BoxOffset<T> {
    /**
     * evaluates
     * @param t tyoe of inner layout
     * @return an {@link Offset}
     */
    Offset evaluate(T t);
}
