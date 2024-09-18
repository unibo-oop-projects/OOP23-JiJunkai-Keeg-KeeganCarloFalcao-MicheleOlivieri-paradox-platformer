package com.project.paradoxplatformer.view.javafx.fxcomponents;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.javafx.fxcomponents.abstracts.AbstractFXGraphicAdapter;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * An adapter for {@code Rectangle} that provides additional functionality
 * for graphical representations of blocks in the JavaFX scene.
 */
public class FXRectangleAdapter extends AbstractFXGraphicAdapter {

    private final Rectangle blockComponent;
    private final SimpleDoubleProperty heightProperty;
    private final SimpleDoubleProperty widthProperty;

    /**
     * Constructs a new {@code FXRectangleAdapter} with the specified parameters.
     *
     * @param id        the unique id of the button
     * @param dimension The {@code Dimension} of the rectangle.
     * @param position  The {@code Coord2D} position of the rectangle.
     * @param fill      The {@code Color} to fill the rectangle.
     * @throws IllegalArgumentException If the underlying UI component is not a
     *                                  {@code Rectangle}.
     */
    protected FXRectangleAdapter(final int id, final Dimension dimension, final Coord2D position, final Color fill) {
        super(id, new Rectangle(), dimension, position);
        this.blockComponent = (Rectangle) super.getUiComponent();
        this.blockComponent.setFill(fill);
        widthProperty = new SimpleDoubleProperty(dimension.width());
        heightProperty = new SimpleDoubleProperty(dimension.height());
        this.setDimension(dimension.width(), dimension.height());
    }

    /**
     * Sets the width and height of the rectangle.
     *
     * @param width  The new width of the rectangle.
     * @param height The new height of the rectangle.
     */
    @Override
    public void setDimension(final double width, final double height) {
        this.widthProperty.set(width);
        this.heightProperty.set(height);
    }

    /**
     * Returns the color of the rectangle if it is an instance of {@code Color}.
     *
     * @return An {@code Optional} containing the {@code Color} of the rectangle if
     *         available, or an empty {@code Optional} otherwise.
     */
    protected Optional<Color> color() {
        return Optional.of(this.blockComponent.getFill())
                .filter(Color.class::isInstance)
                .map(Color.class::cast);
    }

    /**
     * Binds the width and height properties of the rectangle to the specified ratio
     * values.
     *
     * @param wratio The observable ratio for the width.
     * @param hratio The observable ratio for the height.
     */
    @Override
    public void bindProperties(final ObservableDoubleValue wratio, final ObservableDoubleValue hratio) {
        super.bindProperties(wratio, hratio);
        this.blockComponent.heightProperty().bind(heightProperty.multiply(hratio));
        this.blockComponent.widthProperty().bind(widthProperty.multiply(wratio));
    }
}
