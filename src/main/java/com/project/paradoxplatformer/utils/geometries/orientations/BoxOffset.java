package com.project.paradoxplatformer.utils.geometries.orientations;

@FunctionalInterface
public interface BoxOffset<T> {
    Offset evaluate(T t);

}
