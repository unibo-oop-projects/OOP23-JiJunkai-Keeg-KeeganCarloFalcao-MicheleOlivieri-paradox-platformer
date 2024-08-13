package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.obstacles.Wall;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractHarmlessObstacle;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public final class Wall extends AbstractHarmlessObstacle {

    public Wall(Coord2D position, Dimension dimension) {
        super(position, dimension);
    }
}
