package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public abstract class AbstractHarmlessObstacle extends AbstractObstacle{

    protected AbstractHarmlessObstacle(Coord2D position, Dimension dimension) {
        super(position, dimension);
    }
}
