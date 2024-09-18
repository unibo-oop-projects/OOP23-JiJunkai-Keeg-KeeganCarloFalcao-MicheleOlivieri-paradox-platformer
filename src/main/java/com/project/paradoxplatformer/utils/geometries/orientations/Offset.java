package com.project.paradoxplatformer.utils.geometries.orientations;

import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * Such Interface allows to stack multiple offeset from a base one. It has been structured this way
 * to manage the complex offset management of an application. In fact, every basic application is 
 * based on a graphical container and its children so-called nodes, and every node has its own offset
 * being different from the the cointainer one, so if we stack the offset of node (called {@link BoxOffset})
 * with the cointainer one every movement/translation is calculated with both offsets, allowing for further and
 * more complex comutation if there are several offsets in the application.
 */
public interface Offset {

    /**
     * The base offset (current one) is anchored to another one, allowing bottom to top computations.
     * 
     * @param offset anchored offet, current offest must not be null
     * @return {@link Offset}
     */
    Offset anchor(Offset offset);

    /**
     * Getter for the resulting Coord.
     * 
     * @return {@code Coord2D()}
     */
    Coord2D get();
}
