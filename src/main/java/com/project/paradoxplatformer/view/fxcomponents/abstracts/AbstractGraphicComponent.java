package com.project.paradoxplatformer.view.fxcomponents.abstracts;

import java.util.Objects;
import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.fxcomponents.api.GraphicComponent;
import com.project.paradoxplatformer.view.fxcomponents.containers.api.GraphicContainer;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class AbstractGraphicComponent implements GraphicComponent{

    private final Node uiComponent;
    protected final Dimension dimension;
    private double originY;
    private double originX;
    private double x;
    private double y;

    public AbstractGraphicComponent(final Node component, Dimension dimension) {
        this.uiComponent = component;   
        this.dimension = dimension;
        this.originX = this.dimension.width()/2;
        this.originY = this.dimension.height()/2;
    }

    @Override
    public Coord2D position() {
        return new Coord2D(
            this.x - this.originX,
            this.y - this.originY
        );
    }

    @Override
    public abstract void setDimension(final double width, final double height);

    //Must decide wether relative or absolute
    @Override
    public void setPosition(final double x, final double y) {
        this.uiComponent.setTranslateX(x + this.originX);
        this.uiComponent.setTranslateY(y - this.originY);
        this.x = x + originX;
        this.y = y + originY;
    }

    @Override
    public void translate(final double x, final double y) {
        this.setPosition(this.x + x, this.y + y);
    }

    @Override
    public abstract Node unwrap();

    @Override
    public  Dimension dimension() {
        return this.dimension;
    }

    @Override
    public abstract Optional<Image> image();

    @Override
    public abstract Optional<Color> color();

    @Override
    public void setRelativePositionTo(final double x, final double y, final GraphicContainer container) {
        Objects.requireNonNull(container);
        final double containerOriginX = container.dimension().width()/2;
        final double containerOriginY = container.dimension().height()/2;
        this.setPosition(x - containerOriginX , y - containerOriginY);
    }
    
    
}
