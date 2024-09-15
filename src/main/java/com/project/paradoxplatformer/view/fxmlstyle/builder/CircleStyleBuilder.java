package com.project.paradoxplatformer.view.fxmlstyle.builder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A builder class for applying various styles to a {@link Circle}.
 * This class follows the Builder design pattern to allow for fluent
 * and incremental style configuration.
 */
public class CircleStyleBuilder extends AbstractStyleBuilder<Circle> {

    /**
     * Constructs a new CircleStyleBuilder for the given circle.
     * 
     * @param circle The circle to style.
     */
    public CircleStyleBuilder(final Circle circle) {
        super(circle);
    }

    /**
     * Sets the fill color of the circle.
     * 
     * @param color The color to set as the fill, represented by a {@link Color}
     *              object.
     * @return This CircleStyleBuilder instance for chaining.
     */
    public CircleStyleBuilder withFillColor(final Color color) {
        getNode().setFill(color);
        return this;
    }

    /**
     * Sets the stroke color of the circle.
     * 
     * @param color The color to set as the stroke, represented by a {@link Color}
     *              object.
     * @return This CircleStyleBuilder instance for chaining.
     */
    public CircleStyleBuilder withStrokeColor(final Color color) {
        getNode().setStroke(color);
        return this;
    }

    /**
     * Sets the stroke width of the circle.
     * 
     * @param width The width of the stroke, in pixels.
     * @return This CircleStyleBuilder instance for chaining.
     */
    public CircleStyleBuilder withStrokeWidth(final double width) {
        getNode().setStrokeWidth(width);
        return this;
    }

    @Override
    public void apply() {
        // Apply method is empty as styles are applied incrementally.
    }
}
