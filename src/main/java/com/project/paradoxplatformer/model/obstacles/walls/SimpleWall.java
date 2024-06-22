package com.project.paradoxplatformer.model.obstacles.walls;

import java.util.Optional;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.Wall;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.world.Dimension;
import com.project.paradoxplatformer.utils.world.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.world.vector.Polar2DVector;
import com.project.paradoxplatformer.utils.world.vector.api.Vector2D;

public class SimpleWall extends AbstractObstacle implements Wall{

    protected SimpleWall(Coord2D position, Dimension dimension, Optional<TrajectoryInfo> trajStats) {
        super(position, dimension, trajStats);
        //TODO Auto-generated constructor stub
    }

    @Override
    public Vector2D getSpeed() {
        return Polar2DVector.nullVector();
    }
    
    //TODO stretching modifier and stacked update events
    //meaning once one is finished (percetage == 1) then next event begins
}
