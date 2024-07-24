package com.project.paradoxplatformer.view.fxcomponents;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.fxcomponents.abstracts.AbstractFXGraphicAdapter;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FXRectangleAdapter extends AbstractFXGraphicAdapter {

    private final Rectangle blockComponent;
    private final SimpleDoubleProperty heighProperty;
    private final SimpleDoubleProperty widthProperty;
    
    
    protected FXRectangleAdapter(Dimension dimension, Coord2D position, Color fill) {
        super(new Rectangle(), dimension, position);
        if (this.uiComponent instanceof Rectangle blockCopy) {
            this.blockComponent = blockCopy;
            this.blockComponent.setFill(fill);
            widthProperty = new SimpleDoubleProperty(dimension.width());
            heighProperty = new SimpleDoubleProperty(dimension.height());
            //SHOULD DO IF SPRITE SO MAKE DISTINCT CLASSES
            
            this.setDimension(dimension.width(), dimension.height());
        } else {
            throw new IllegalArgumentException("Require rectangle");
        }
    }

    @Override
    public void setDimension(final double width, final double height) {
        
        this.widthProperty.set(width);
        this.heighProperty.set(height); 
    }

    protected Optional<Color> color() {
        return Optional.of(this.blockComponent.getFill())
            .filter(Color.class::isInstance)
            .map(Color.class::cast);
    }

    @Override
    public void bindPropreties(ObservableDoubleValue wratio, ObservableDoubleValue hratio) {
        super.bindPropreties(wratio, hratio);
        this.blockComponent.heightProperty().bind(heighProperty.multiply(hratio));
        this.blockComponent.widthProperty().bind(widthProperty.multiply(wratio));
        
        
    }

}
