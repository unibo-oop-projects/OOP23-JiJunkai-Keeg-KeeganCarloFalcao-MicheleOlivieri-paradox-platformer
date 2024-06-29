package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public interface GameObject {

    public Coord2D getPosition();

    public Dimension getDimension();

}