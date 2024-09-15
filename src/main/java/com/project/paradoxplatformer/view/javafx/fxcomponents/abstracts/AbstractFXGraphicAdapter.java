package com.project.paradoxplatformer.view.javafx.fxcomponents.abstracts;

import com.project.paradoxplatformer.utils.SecureWrapper;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.scene.Node;

/**
 * Abstract base class for FX graphical adapters, providing common functionality
 * for handling graphical components in a JavaFX application.
 */
public abstract class AbstractFXGraphicAdapter implements GraphicAdapter<Node> {

    private static final double INVERTED_FACTOR = -1.0;

    private final Node uiComponent;
    private final Dimension dimension;
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private final Coord2D bindedPosition;
    private int key;

    /**
     * Constructs an AbstractFXGraphicAdapter with the specified component,
     * dimension, and position.
     *
     * @param component   The Node component to be managed.
     * @param dimension   The dimension of the graphical component.
     * @param relativePos The relative position of the graphical component.
     */
    protected AbstractFXGraphicAdapter(Node component, Dimension dimension, Coord2D relativePos) {
        this.uiComponent = component;
        this.dimension = dimension;
        this.xProperty = new SimpleDoubleProperty(relativePos.x());
        this.yProperty = new SimpleDoubleProperty(relativePos.y());
        this.bindedPosition = relativePos;
    }

    @Override
    public Coord2D absolutePosition() {
        return new Coord2D(
                this.bindedPosition.x(),
                this.bindedPosition.y());
    }

    @Override
    public Coord2D relativePosition() {
        return new Coord2D(
                this.xProperty.doubleValue(),
                this.yProperty.doubleValue());
    }

    /**
     * Sets the dimension of the graphical component.
     *
     * @param width  The new width of the component.
     * @param height The new height of the component.
     */
    @Override
    public abstract void setDimension(double width, double height);

    /**
     * Sets the position of the graphical component.
     *
     * @param x The new x-coordinate.
     * @param y The new y-coordinate.
     */
    @Override
    public void setPosition(double x, double y) {
        this.xProperty.set(x);
        this.yProperty.set(y);
    }

    /**
     * Translates the position of the graphical component by the specified amounts.
     *
     * @param x The amount to translate in the x direction.
     * @param y The amount to translate in the y direction.
     */
    @Override
    public void translate(double x, double y) {
        this.setPosition(this.absolutePosition().x() + x, this.absolutePosition().y() + y);
    }

    @Override
    public Node unwrap() {
        return SecureWrapper.of(this.uiComponent).get();
    }

    @Override
    public Dimension dimension() {
        return this.dimension;
    }

    /**
     * Flips the graphical component by inverting its scale along the x-axis.
     */
    @Override
    public void flip() {
        this.uiComponent.setScaleX(INVERTED_FACTOR * uiComponent.getScaleX());
    }

    /**
     * Binds the width and height properties of the graphical component to the
     * specified ratios.
     *
     * @param wratio The observable ratio for the width.
     * @param hRatio The observable ratio for the height.
     */
    @Override
    public void bindProperties(ObservableDoubleValue wratio, ObservableDoubleValue hRatio) {
        this.uiComponent.translateYProperty().bind(yProperty.multiply(hRatio));
        this.uiComponent.translateXProperty().bind(xProperty.multiply(wratio));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dimension == null) ? 0 : dimension.hashCode());
        result = prime * result + ((xProperty == null) ? 0 : xProperty.hashCode());
        result = prime * result + ((yProperty == null) ? 0 : yProperty.hashCode());
        return result;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public int getID() {
        return this.key;
    }

    /**
     * Gets the node component.
     * 
     * @return an {@link Node} component assocciated.
     */
    public Node getUiComponent() {
        return uiComponent;
    }

}
