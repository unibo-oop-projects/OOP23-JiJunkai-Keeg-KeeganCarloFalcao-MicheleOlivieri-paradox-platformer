package com.project.paradoxplatformer.utils.geometries.orientations;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

@FunctionalInterface
public interface OffsetCorrector {
    Coord2D correct(Dimension gComponent, Coord2D coord);

}
