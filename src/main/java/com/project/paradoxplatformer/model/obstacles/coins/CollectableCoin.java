package com.project.paradoxplatformer.model.obstacles.coins;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.coins.factory.abstracts.AbstractCoin;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import java.util.Queue;

public class CollectableCoin extends AbstractCoin {

    private final PlayerModel actor;

    public CollectableCoin(
        final Coord2D position,
        final Dimension dimension,
        final Queue<TrajectoryInfo> moveStats,
        final PlayerModel actor
    ) {
        super(position, dimension, moveStats);
        this.actor = actor;
    }
    
    @Override
    public void effect() {
        super.effect();
        this.actor.collectCoin();
    }

    @Override
    public void updateState(long dt) {
        super.updateState(dt);
    }
    
}
