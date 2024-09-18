package com.project.paradoxplatformer.view.javafx.fxcomponents.abstracts;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
    private final DoubleProperty xProperty;
    private final DoubleProperty yProperty;
    private final Coord2D bindedPosition;
    private final int key;

    /**
     * Constructs an AbstractFXGraphicAdapter with the specified component,
     * dimension, and position.
     *
     * @param id          the unique id of the button
     * @param component   The Node component to be managed.
     * @param dimension   The dimension of the graphical component.
     * @param relativePos The relative position of the graphical component.
     */
    protected AbstractFXGraphicAdapter(final int id, final Node component, final Dimension dimension,
                                       final Coord2D relativePos) {
        this.key = id;
        this.uiComponent = component;
        this.dimension = dimension;
        this.xProperty = new SimpleDoubleProperty(relativePos.x());
        this.yProperty = new SimpleDoubleProperty(relativePos.y());
        this.bindedPosition = relativePos;
    }

    /**
     * Returns the absolute position of the graphical component.
     *
     * @return the absolute position as a Coord2D instance
     */
    @Override
    public Coord2D absolutePosition() {
        return new Coord2D(
                this.bindedPosition.x(),
                this.bindedPosition.y());
    }

    /**
     * Returns the relative position of the graphical component.
     *
     * @return the relative position as a Coord2D instance
     */
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
    public void setPosition(final double x, final double y) {
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
    public void translate(final double x, final double y) {
        this.setPosition(this.absolutePosition().x() + x, this.absolutePosition().y() + y);
    }

    /**
     * Returns the wrapped Node component.
     *
     * @return the Node component managed by this adapter
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP", 
        justification = 
            "The sole purpose of this method is to retrieve the underlying node of the graphics adapter" 
            + "to add it to the children of the container, so it is guaranteed that"
            + "the caller will not perform rewrite actions on the current graphics component."
            + "Furthermore the javafx nodes do not have an accessible deep copy method and"
            + "therefore manually copying an external component is not preferable as it may cause"
            + "unwanted behavior."
    )
    @Override
    public Node unwrap() {
        return this.uiComponent;
    }

    /**
     * Returns the dimension of the graphical component.
     *
     * @return the dimension as a Dimension instance
     */
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
    public void bindProperties(final ObservableDoubleValue wratio, final ObservableDoubleValue hRatio) {
        this.uiComponent.translateYProperty().bind(yProperty.multiply(hRatio));
        this.uiComponent.translateXProperty().bind(xProperty.multiply(wratio));
    }

    /**
     * Calculates the hash code for the graphical adapter based on its properties.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dimension == null) ? 0 : dimension.hashCode());
        result = prime * result + ((xProperty == null) ? 0 : xProperty.hashCode());
        result = prime * result + ((yProperty == null) ? 0 : yProperty.hashCode());
        return result;
    }

    /**
     * Returns the ID associated with the graphical adapter.
     *
     * @return the ID
     */
    @Override
    public int getID() {
        return this.key;
    }

    /**
     * Gets the node component.
     *
     * @return an {@link Node} component associated.
     */
    protected Node getUiComponent() {
        return Optional.of(uiComponent).get();
    }

    /**
     * Checks if this graphical adapter is equal to another object. The comparison
     * is based on the properties of the graphical adapter.
     *
     * @param obj the object to compare with
     * @return true if this graphical adapter is equal to the specified object
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final AbstractFXGraphicAdapter other = (AbstractFXGraphicAdapter) obj;
        return key == other.key
                && dimension.equals(other.dimension)
                && xProperty.get() == other.xProperty.get()
                && yProperty.get() == other.yProperty.get();
    }

}
