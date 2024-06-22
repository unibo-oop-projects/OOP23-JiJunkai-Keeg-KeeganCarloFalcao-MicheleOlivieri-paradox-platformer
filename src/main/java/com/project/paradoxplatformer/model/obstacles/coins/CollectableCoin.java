package com.project.paradoxplatformer.model.obstacles.coins;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.coins.factory.abstracts.AbstractCoin;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.world.Dimension;
import com.project.paradoxplatformer.utils.world.coordinates.Coord2D;
import java.util.Optional;

public class CollectableCoin extends AbstractCoin {

    private final PlayerModel actor;

    public CollectableCoin(
        final Coord2D position,
        final Dimension dimension,
        final Optional<TrajectoryInfo> moveStats,
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
        this.position = new Coord2D(this.displacement.xComponent(), this.displacement.yComponent());
    }
    
}
