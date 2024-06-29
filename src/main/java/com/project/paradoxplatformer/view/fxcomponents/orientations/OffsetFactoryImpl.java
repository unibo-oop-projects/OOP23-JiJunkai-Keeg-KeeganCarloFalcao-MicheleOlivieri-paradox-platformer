package com.project.paradoxplatformer.view.fxcomponents.orientations;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public class OffsetFactoryImpl implements OffsetFactory {

    private final Dimension anchored;

    public OffsetFactoryImpl(Dimension anchordDim) {
        this.anchored = anchordDim;
    }

    @Override
    public Offset topLeft() {
        return (x, y) -> new Coord2D(x, y);
    }

    @Override
    public Offset bottomRight() {
        return (x, y) -> new Coord2D(anchored.width() - x, anchored.height() - y);
    }

    @Override
    public Offset bottomLeft() {
        return (x, y) -> new Coord2D(x, anchored.height() - y);
    }

    @Override
    public Offset topRight() {
        return (x, y) -> new Coord2D(anchored.width() - x, y);
    }
    
}
