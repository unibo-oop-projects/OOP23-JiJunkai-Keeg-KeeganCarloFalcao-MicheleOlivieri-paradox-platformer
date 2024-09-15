package com.project.paradoxplatformer.utils.geometries.orientations;

/**
 * Functional interface for evaluating an offset based on a given input.
 * 
 * @param <T> the type of the input used to evaluate the offset
 */
@FunctionalInterface
public interface BoxOffset<T> {

    /**
     * Evaluates an offset based on the provided input.
     * 
     * @param t the input of type {@code T} used for evaluation
     * @return the resulting {@link Offset} after evaluation
     */
    Offset evaluate(T t);
}