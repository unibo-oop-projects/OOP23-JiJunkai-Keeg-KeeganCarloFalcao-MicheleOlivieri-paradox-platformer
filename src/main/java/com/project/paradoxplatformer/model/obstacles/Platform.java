package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractHarmlessObstacle;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public final class Platform extends AbstractHarmlessObstacle{

    public Platform(Coord2D position, Dimension dimension) {
        super(position, dimension);
    }
}
