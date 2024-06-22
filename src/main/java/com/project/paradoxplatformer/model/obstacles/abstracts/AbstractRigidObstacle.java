package com.project.paradoxplatformer.model.obstacles.abstracts;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.utils.collision.api.Collidable;
import com.project.paradoxplatformer.utils.world.Dimension;
import com.project.paradoxplatformer.utils.world.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.world.vector.Polar2DVector;
import com.project.paradoxplatformer.utils.world.vector.api.Vector2D;

public class AbstractRigidObstacle extends AbstractObstacle implements Collidable{

    protected AbstractRigidObstacle(Coord2D position, Dimension dimension, Queue<TrajectoryInfo> trajStats) {
        super(position, dimension, trajStats);

        
    }

    @Override
    public Vector2D getSpeed() {
        return Polar2DVector.nullVector();
    }

    @Override
    public Dimension getDimension() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSize'");
    }

    @Override
    public void handleCollision(Collidable other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleCollision'");
    }
    
}
