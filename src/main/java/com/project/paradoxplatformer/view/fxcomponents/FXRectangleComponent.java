package com.project.paradoxplatformer.view.fxcomponents;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.fxcomponents.abstracts.AbstractGraphicComponent;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FXRectangleComponent extends AbstractGraphicComponent {

    private final Rectangle blockComponent;
    
    public FXRectangleComponent(Dimension dimension, Coord2D position, Color fill) {
        super(new Rectangle(), dimension, position);
        if (this.uiComponent instanceof Rectangle blockCopy) {
            this.blockComponent = blockCopy;      
            this.blockComponent.setFill(fill);
            this.setDimension(dimension.width(), dimension.height());
            
        } else {
            throw new IllegalArgumentException("Require rectangle");
        }
    }

    @Override
    public void setDimension(final double width, final double height) {
        this.blockComponent.setWidth(width);
        this.blockComponent.setHeight(height);
    }

    @Override
    public Node unwrap() {
        return this.blockComponent;
    }

    protected Optional<Color> color() {
        return Optional.of(this.blockComponent.getFill())
            .filter(Color.class::isInstance)
            .map(Color.class::cast);
    }

}
