package com.project.paradoxplatformer.utils.geometries.orientations;

import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public interface Offset {

    Offset anchor(Offset offset);

    Coord2D get();
}



