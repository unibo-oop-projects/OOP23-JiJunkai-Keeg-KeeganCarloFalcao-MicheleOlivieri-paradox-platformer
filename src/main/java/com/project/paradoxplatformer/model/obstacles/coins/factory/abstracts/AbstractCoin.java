package com.project.paradoxplatformer.model.obstacles.coins.factory.abstracts;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.Coin;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

import java.util.Queue;

public abstract class AbstractCoin extends AbstractObstacle implements Coin{

    public AbstractCoin(final Coord2D position, final Dimension dimension, final Queue<TrajectoryInfo> trajStats) {
        super(position, dimension, trajStats);
    }

    @Override
    public Vector2D getSpeed() {
        //Why you need speed when it's all done by updatestate
        return Polar2DVector.nullVector();
    }

    @Override
    public void effect() {
        super.effect();
    }

    @Override
    public void updateState(long dt) {
        super.updateState(dt);
    }

}
