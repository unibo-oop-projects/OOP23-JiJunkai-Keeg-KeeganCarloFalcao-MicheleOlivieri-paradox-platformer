package com.project.paradoxplatformer.view.graphics;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.renders.ViewComponent;

import javafx.beans.value.ObservableDoubleValue;

/**
 * Interface for graphical adapters that manage graphical representations of
 * objects.
 *
 * @param <C> The type of the graphical component.
 */
public interface GraphicAdapter<C> extends ViewComponent<C> {

    /**
     * Returns the dimension of the graphical component.
     *
     * @return The dimension of the graphical component.
     */
    Dimension dimension();

    /**
     * Returns the absolute position of the graphical component.
     *
     * @return The absolute position of the graphical component.
     */
    Coord2D absolutePosition();

    /**
     * Returns the relative position of the graphical component.
     *
     * @return The relative position of the graphical component.
     */
    Coord2D relativePosition();

    /**
     * Sets the dimension of the graphical component.
     *
     * @param width  The new width of the graphical component.
     * @param height The new height of the graphical component.
     */
    void setDimension(double width, double height);

    /**
     * Sets the position of the graphical component.
     *
     * @param x The new x-coordinate of the graphical component.
     * @param y The new y-coordinate of the graphical component.
     */
    void setPosition(double x, double y);

    /**
     * Translates the position of the graphical component by the specified amounts.
     *
     * @param x The amount to translate in the x direction.
     * @param y The amount to translate in the y direction.
     */
    void translate(double x, double y);

    /**
     * Binds the width and height properties of the graphical component to the
     * specified ratios.
     *
     * @param wRatio The observable ratio for the width.
     * @param hRatio The observable ratio for the height.
     */
    void bindProperties(ObservableDoubleValue wRatio, ObservableDoubleValue hRatio);

    /**
     * Flips the graphical component.
     */
    void flip();

    /**
     * Returns the key identifier for the graphical component.
     *
     * @return The key identifier.
     */
    int getID();

}
