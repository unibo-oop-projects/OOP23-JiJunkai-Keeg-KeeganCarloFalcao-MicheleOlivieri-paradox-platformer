package com.project.paradoxplatformer.model.obstacles.coins.factory;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.Coin;
import com.project.paradoxplatformer.model.obstacles.coins.CollectableCoin;
import com.project.paradoxplatformer.model.obstacles.coins.ExplodingCoin;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.world.Dimension;
import com.project.paradoxplatformer.utils.world.coordinates.Coord2D;
import java.util.Optional;

public class CoinFactoryImpl implements CoinFactory {

    @Override
    public Coin collectableCoin(final Coord2D entrypoint, final Dimension dimension, final Optional<TrajectoryInfo> trajStats, final PlayerModel player) {
        return new CollectableCoin(entrypoint, dimension, trajStats, player);
    }

    @Override
    public Coin explodingCoin(final Coord2D entrypoint, final Dimension dimension, final Optional<TrajectoryInfo> trajStats) {
        return new ExplodingCoin(entrypoint, dimension, trajStats);
    }

    @Override
    public Coin platformCoin(final Coord2D entrypoint, final Dimension dimension, final Optional<TrajectoryInfo> trajStats) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'platformCoin'");
    }

    
    
}
