package com.project.paradoxplatformer.view.graphics;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.renders.ViewComponent;

import javafx.beans.value.ObservableDoubleValue;

public interface GraphicAdapter<C> extends ViewComponent<C> {

    Dimension dimension();

    Coord2D absolutePosition();

    Coord2D relativePosition();

    void setDimension(final double width, final double height);

    void setPosition(final double x, final double y);

    void translate(final double x, final double y);

    void bindPropreties(ObservableDoubleValue wRatio, ObservableDoubleValue hRatio);

    void flip();

    void setKey(int key);

    int getID();

    default boolean equals(GraphicAdapter<C> other) {
        return other.dimension().equals(this.dimension()) && other.relativePosition().equals(this.relativePosition());
    }
    
}
