package com.project.paradoxplatformer.view.graphics;

import java.util.Objects;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import javafx.beans.value.ObservableDoubleValue;

public class ReadOnlyGraphicDecorator<C> implements GraphicAdapter<C>{

    private final GraphicAdapter<C> graphicReader;

    public ReadOnlyGraphicDecorator(final GraphicAdapter<C> copyGraphic) {
        Objects.requireNonNull(copyGraphic);
        this.graphicReader = (copyGraphic);
    }

    @Override
    public C unwrap() {
        return this.graphicReader.unwrap();
    }

    @Override
    public Dimension dimension() {
        return this.graphicReader.dimension();
    }

    @Override
    public Coord2D absolutePosition() {
        return this.graphicReader.absolutePosition();
    }

    @Override
    public Coord2D relativePosition() {
        return this.graphicReader.relativePosition();
    }

    @Override
    public void setDimension(double width, double height) {
        throw new UnsupportedOperationException("Unable to execute method 'setDimension'");
    }

    @Override
    public void setPosition(double x, double y) {
        throw new UnsupportedOperationException("Unable to execute 'setPosition'");
    }

    @Override
    public void translate(double x, double y) {
        throw new UnsupportedOperationException("Unable to execute 'translate'");
    }

    @Override
    public void bindPropreties(ObservableDoubleValue wRatio, ObservableDoubleValue hRatio) {
        throw new UnsupportedOperationException("Unable to execute 'bindPropreties'");
    }

    @Override
    public void flip() {
        throw new UnsupportedOperationException("Unable to execute method 'flip'");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((graphicReader == null) ? 0 : graphicReader.hashCode());
        return result;
    }

    @Override
    public int getID() {
        return graphicReader.getID();
    }
    
}
