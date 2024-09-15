package com.project.paradoxplatformer.view.graphics;

import com.project.paradoxplatformer.controller.input.api.KeyInputer;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.renders.ViewComponent;

import javafx.beans.value.ObservableDoubleValue;

/**
 * Represents a container for graphical components that can be rendered and
 * manipulated.
 * 
 * @param <T> the type of data associated with the graphical component
 * @param <K> the type of input key for handling key events
 */
public interface GraphicContainer<T, K> extends KeyInputer<K> {

    /**
     * Gets the current dimensions of the container.
     * 
     * @return the dimensions of the container
     */
    Dimension dimension();

    /**
     * Sets the dimensions of the container.
     * 
     * @param width  the width of the container
     * @param height the height of the container
     */
    void setDimension(double width, double height);

    /**
     * Renders the specified graphical component within the container.
     * 
     * @param component the graphical component to render
     * @return {@code true} if the component was successfully rendered,
     *         {@code false} otherwise
     */
    boolean render(ViewComponent<T> component);

    /**
     * Deletes the specified graphical component from the container.
     * 
     * @param component the graphical component to delete
     * @return {@code true} if the component was successfully deleted, {@code false}
     *         otherwise
     */
    boolean delete(ViewComponent<T> component);

    /**
     * Gets the observable property representing the width of the container.
     * 
     * @return an observable property for the width
     */
    ObservableDoubleValue widthProperty();

    /**
     * Gets the observable property representing the height of the container.
     * 
     * @return an observable property for the height
     */
    ObservableDoubleValue heightProperty();
}
