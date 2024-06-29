package com.project.paradoxplatformer.view.fxcomponents.orientations;

import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public interface Offset {
    Coord2D anchor(final double x, final double y);
}
