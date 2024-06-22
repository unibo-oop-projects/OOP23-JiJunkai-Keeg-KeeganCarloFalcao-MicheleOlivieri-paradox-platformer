package com.project.paradoxplatformer.model.obstacles.coins.factory.abstracts;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.Coin;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.world.Dimension;
import com.project.paradoxplatformer.utils.world.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.world.vector.Polar2DVector;
import com.project.paradoxplatformer.utils.world.vector.api.Vector2D;
import java.util.Optional;

public abstract class AbstractCoin extends AbstractObstacle implements Coin{

    public AbstractCoin(final Coord2D position, final Dimension dimension, final Optional<TrajectoryInfo> trajStats) {
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
