package com.project.paradoxplatformer.utils.geometries.orientations.factory;

import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.orientations.Offset;

/**
 * Represents a base offset, which is used to anchor other offsets.
 * This class provides a default implementation of the {@link Offset} interface.
 */
public final class BaseOffset implements Offset {

    /**
     * Constructs a new {@code BaseOffset}. The constructor is protected to prevent
     * direct instantiation from outside the package.
     */
    protected BaseOffset() {
//        No params needed.
    }

    /**
     * Anchors this base offset to another offset.
     * 
     * @param offset the offset to anchor
     * @return a new {@link Offset} that represents the anchored result
     * @throws IllegalStateException if anchoring is not supported
     */
    @Override
    public Offset anchor(final Offset offset) {
        throw new IllegalStateException("CANT ANCHOR BASE OFFSET");
    }

    /**
     * Returns the coordinates of this base offset.
     * 
     * @return a {@link Coord2D} representing the coordinates of this offset
     */
    @Override
    public Coord2D get() {
        return new Coord2D(0, 0);
    }
}
