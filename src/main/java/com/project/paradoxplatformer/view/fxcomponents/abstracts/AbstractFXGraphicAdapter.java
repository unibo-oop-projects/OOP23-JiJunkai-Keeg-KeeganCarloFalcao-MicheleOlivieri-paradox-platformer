package com.project.paradoxplatformer.view.fxcomponents.abstracts;

import com.project.paradoxplatformer.utils.SecureWrapper;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.scene.Node;

public abstract class AbstractFXGraphicAdapter implements GraphicAdapter{

    protected final Node uiComponent;
    protected final Dimension dimension;
    protected DoubleProperty xProperty;
    protected DoubleProperty yProperty;

    protected AbstractFXGraphicAdapter(final Node component, Dimension dimension, Coord2D relativePos) {
        this.uiComponent = component;   
        this.dimension = dimension;
        this.xProperty = new SimpleDoubleProperty(relativePos.x());
        this.yProperty = new SimpleDoubleProperty(relativePos.y());
        
    }


    @Override
    public Coord2D absolutePosition() {
        return new Coord2D(
            this.uiComponent.getTranslateX(),
            this.uiComponent.getTranslateY()
        );
    }

    @Override
    public Coord2D relativePosition() {
        return new Coord2D(
            this.xProperty.doubleValue(),
            this.yProperty.doubleValue() 
        );
    }
    @Override
    public abstract void setDimension(final double width, final double height);

    //Must decide wether relative or absolute
    @Override
    public void setPosition(final double x, final double y) {
        
        this.xProperty.set(x);
        this.yProperty.set(y);
        
    }

    @Override
    public void translate(final double x, final double y) {
        this.setPosition(this.absolutePosition().x() + x, this.absolutePosition().y() + y);
    }

    @Override
    public Node unwrap() {
        return SecureWrapper.of(this.uiComponent).get();
    }

    @Override
    public  Dimension dimension() {
        return this.dimension;
    }

    @Override
    public void flip() {
        this.uiComponent.setScaleX(-1  * uiComponent.getScaleX());
    }

    @Override 
    public void bindPropreties(ObservableDoubleValue wratio, ObservableDoubleValue hRatio) {
        this.uiComponent.translateYProperty().bind(yProperty.multiply(hRatio));
        this.uiComponent.translateXProperty().bind(xProperty.multiply(wratio));
    }
    
    
}
