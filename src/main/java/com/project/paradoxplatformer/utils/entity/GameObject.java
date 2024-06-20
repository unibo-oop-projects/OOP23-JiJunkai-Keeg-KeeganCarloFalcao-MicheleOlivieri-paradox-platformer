package com.project.paradoxplatformer.utils.entity;

import com.project.paradoxplatformer.utils.world.Dimension;
import com.project.paradoxplatformer.utils.world.coordinates.Coord2D;

public interface GameObject {

    Coord2D getPosition();

    Dimension getDimension();

}