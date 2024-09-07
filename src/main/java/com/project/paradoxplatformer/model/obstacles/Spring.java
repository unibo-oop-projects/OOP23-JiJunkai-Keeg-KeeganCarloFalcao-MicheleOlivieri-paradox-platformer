package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public class Spring extends AbstractObstacle {

    public Spring(Coord2D position, Dimension dimension) {
        super(position, dimension);
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.SPRINGS;
    }
    
}
