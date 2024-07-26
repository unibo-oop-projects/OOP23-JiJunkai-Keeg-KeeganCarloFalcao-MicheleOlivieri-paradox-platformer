package com.project.paradoxplatformer.model.obstacles;

import java.util.Optional;
import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public final class Platform extends AbstractObstacle{

    public Platform(Coord2D position, Dimension dimension, Queue<TrajectoryInfo> trajStats) {
        super(position, dimension, trajStats);
    }

    @Override
    public void effect(Optional<ControllableObject> ob) {
        super.effect(ob);
    }

    @Override
    public Vector2D getSpeed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSpeed'");
    }
    
}
