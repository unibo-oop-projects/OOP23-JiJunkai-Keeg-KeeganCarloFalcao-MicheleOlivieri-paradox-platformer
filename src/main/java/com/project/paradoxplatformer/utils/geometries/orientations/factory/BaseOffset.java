package com.project.paradoxplatformer.utils.geometries.orientations.factory;

import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.orientations.Offset;

/**
 * Base offset, needed to anchor other offsets
 */
public final class BaseOffset implements Offset {
        
    protected BaseOffset() {}

    @Override
    public Offset anchor(Offset offset) {
        throw new IllegalStateException("CANT ANCHOR BASE OFFSET");
    }

    @Override
    public Coord2D get() {
        return new Coord2D(0, 0);
    }
}