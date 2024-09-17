package com.project.paradoxplatformer.utils.geometries.orientations;

import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * Offset.
 */
public interface Offset {

    /**
     * anchor to an offset.
     * 
     * @param offset anchored offet, current offest must not be null
     * @return {@link Offset}
     */
    Offset anchor(Offset offset);

    /**
     * Getter for a new Coord.
     * 
     * @return {@code Coord2D()}
     */
    Coord2D get();
}
