package com.project.paradoxplatformer.view.renders;

/**
 * An interface concernig type of view control that should be adopted
 */
@FunctionalInterface
public interface ViewComponent<T> {
    /**
     * get the type of view control, could be a node for fx, or component for awt, or string for console
     * Cannot generilse unfortunatly, both controls inherit from object
     * @return
     */
    T unwrap();
}
