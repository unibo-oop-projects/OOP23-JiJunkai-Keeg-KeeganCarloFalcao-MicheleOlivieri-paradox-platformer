package com.project.paradoxplatformer.model.obstacles.platforms;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.collision.api.Collidable;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public abstract class AbstractPlatfrom extends AbstractObstacle implements Collidable{

    protected AbstractPlatfrom(Coord2D position, Dimension dimension, Queue<TrajectoryInfo> trajStats) {
        super(position, dimension, trajStats);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Vector2D getSpeed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSpeed'");
    }

    @Override
    public void handleCollision(Collidable other) {
        //Other y acceleration must be opposite to gravity
    }
    
}
