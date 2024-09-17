package com.project.paradoxplatformer.utils.geometries.orientations;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * Corrects a coord based on dimension.
 */
@FunctionalInterface
public interface OffsetCorrector {
    /**
     * Corrects.
     * 
     * @param boxDimension dimensions
     * @param elementCoord the wrong coords to be corrected
     * @return a new Coorected coord
     */
    Coord2D correct(Dimension boxDimension, Coord2D elementCoord);

}
