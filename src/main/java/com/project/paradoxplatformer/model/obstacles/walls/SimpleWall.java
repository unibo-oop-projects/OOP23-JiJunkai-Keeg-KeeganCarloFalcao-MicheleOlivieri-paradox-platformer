package com.project.paradoxplatformer.model.obstacles.walls;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.Wall;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractRigidObstacle;
import com.project.paradoxplatformer.utils.collision.api.Collidable;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public class SimpleWall extends AbstractRigidObstacle implements Wall{

    protected SimpleWall(Coord2D position, Dimension dimension, Queue<TrajectoryInfo> trajStats) {
        super(position, dimension, trajStats);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Vector2D getSpeed() {
        return Polar2DVector.nullVector();
    }

    @Override
    public void effect() {
        super.effect();
    }

    @Override
    public void updateState(final long dt) {
        super.updateState(dt);
    }

    @Override 
    public void handleCollision(Collidable other) {
        super.handleCollision(other);
        //other x accelereation must be 0
    }
}
