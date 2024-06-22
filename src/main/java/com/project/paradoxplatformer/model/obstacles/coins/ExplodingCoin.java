package com.project.paradoxplatformer.model.obstacles.coins;

import java.util.Optional;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.coins.factory.abstracts.AbstractCoin;
import com.project.paradoxplatformer.utils.world.Dimension;
import com.project.paradoxplatformer.utils.world.coordinates.Coord2D;

public class ExplodingCoin extends AbstractCoin{

    public ExplodingCoin(
        final Coord2D position,
        final Dimension dimension, 
        final Optional<TrajectoryInfo> trajStats
        //hypotetic logic class
    ) {
        super(position, dimension, trajStats);
        //this.logic = logic;
    }

    @Override
    public void effect() {
        super.effect(); //should not move
        //this.logic.endgame();
    }

    @Override
    public void updateState(long dt) {
        super.updateState(dt);
        this.position = new Coord2D(this.displacement.xComponent(), this.displacement.yComponent());
    }
    
}
