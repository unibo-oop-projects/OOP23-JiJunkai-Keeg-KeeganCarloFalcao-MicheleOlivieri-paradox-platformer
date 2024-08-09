package com.project.paradoxplatformer.model.obstacles.abstracts;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public abstract class AbstractRigidObstacle extends AbstractObstacle {

    protected AbstractRigidObstacle(final Coord2D position, final Dimension dimension,
            Queue<TrajectoryInfo> trajStats) {
        super(position, dimension, trajStats);
    }

    @Override
    public Vector2D getSpeed() {
        return Polar2DVector.nullVector();
    }

}
