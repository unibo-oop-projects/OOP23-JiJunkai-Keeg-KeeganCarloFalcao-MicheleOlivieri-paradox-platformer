package com.project.paradoxplatformer.view.fxcomponents;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class AbstractGraphicComponent implements GraphicComponent{

    private final Node uiComponent;

    public AbstractGraphicComponent(final Node component) {
        this.uiComponent = component;   
    }

    @Override
    public Coord2D position() {
        return new Coord2D(this.uiComponent.getLayoutX(), this.uiComponent.getLayoutY());
    }

    @Override
    public void setDimension(final double width, final double height) {
        this.uiComponent.prefWidth(width);
        this.uiComponent.prefHeight(height);
    }

    //Must decide wether relative or absolute
    @Override
    public void setPosition(double x, double y) {
        this.uiComponent.setTranslateX(x);
        this.uiComponent.setTranslateY(y);
    }


    @Override
    public abstract Node unwrap();

    @Override
    public abstract Dimension dimension();

    @Override
    public abstract Optional<Image> image();

    @Override
    public abstract Optional<Color> color();
    
}
