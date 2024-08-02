package com.project.paradoxplatformer.model.obstacles;

import java.util.Optional;
import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.obstacles.Wall;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractRigidObstacle;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public final class Wall extends AbstractRigidObstacle {

    public Wall(Coord2D position, Dimension dimension, Queue<TrajectoryInfo> trajStats) {
        super(position, dimension, trajStats);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Vector2D getSpeed() {
        return Polar2DVector.nullVector();
    }

    @Override
    public void effect(Optional<ControllableObject> ob) {
        super.effect(ob);
        ob.ifPresent(ControllableObject::stop);
    }

    @Override
    public void updateState(final long dt) {
        super.updateState(dt);
    }
}
