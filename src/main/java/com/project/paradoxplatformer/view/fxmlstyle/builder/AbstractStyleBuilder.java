package com.project.paradoxplatformer.view.fxmlstyle.builder;

/**
 * An abstract base class for applying various styles to JavaFX nodes.
 * This class provides common functionality for styling and is extended by
 * specific builders for different types of nodes.
 *
 * @param <T> The type of node to style.
 */
public abstract class AbstractStyleBuilder<T> {
    protected final T node; // The node to which styles will be applied

    /**
     * Constructs a new AbstractStyleBuilder for the given node.
     * 
     * @param node The node to style.
     */
    public AbstractStyleBuilder(T node) {
        this.node = node;
    }

    /**
     * Applies the accumulated styles to the node.
     * This method must be implemented by subclasses to apply styles.
     */
    public abstract void apply();
}
