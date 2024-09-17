package com.project.paradoxplatformer.view.renders;

/**
 * An interface representing a type of view control. It is used to define the
 * type of view control that should be adopted, which could be a node for
 * JavaFX,
 * a component for AWT, or a string for the console.
 * 
 * @param <T> The type of view control.
 */
@FunctionalInterface
public interface ViewComponent<T> {

    /**
     * Retrieves the view control of the specified type. This could be a node for
     * JavaFX,
     * a component for AWT, or a string for the console.
     * Unfortunately, it cannot be generalized as both controls inherit from Object.
     *
     * @return The view control of type {@code T}.
     */
    T unwrap();
}
