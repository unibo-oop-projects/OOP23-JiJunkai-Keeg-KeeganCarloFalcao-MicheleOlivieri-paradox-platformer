package com.project.paradoxplatformer.model.obstacles.coins.factory;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.Coin;
import com.project.paradoxplatformer.model.obstacles.coins.CollectableCoin;
import com.project.paradoxplatformer.model.obstacles.coins.ExplodingCoin;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import java.util.Queue;

public class CoinFactoryImpl implements CoinFactory {

    @Override
    public Coin collectableCoin(final Coord2D entrypoint, final Dimension dimension, final Queue<TrajectoryInfo> trajStats, final PlayerModel player) {
        return new CollectableCoin(entrypoint, dimension, trajStats, player);
    }

    @Override
    public Coin explodingCoin(final Coord2D entrypoint, final Dimension dimension, final Queue<TrajectoryInfo> trajStats) {
        return new ExplodingCoin(entrypoint, dimension, trajStats);
    }

    @Override
    public Coin platformCoin(final Coord2D entrypoint, final Dimension dimension, final Queue<TrajectoryInfo> trajStats) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'platformCoin'");
    }

    
    
}
