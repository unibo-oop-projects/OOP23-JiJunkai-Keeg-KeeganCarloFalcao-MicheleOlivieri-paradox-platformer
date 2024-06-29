package com.project.paradoxplatformer.model.obstacles;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.platforms.AbstractPlatfrom;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public class Platform extends AbstractPlatfrom{

    public Platform(Coord2D position, Dimension dimension, Queue<TrajectoryInfo> trajStats) {
        super(position, dimension, trajStats);
    }
    
}
