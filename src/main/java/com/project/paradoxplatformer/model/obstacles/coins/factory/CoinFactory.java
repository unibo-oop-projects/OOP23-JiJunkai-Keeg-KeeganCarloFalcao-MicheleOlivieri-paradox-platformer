package com.project.paradoxplatformer.model.obstacles.coins.factory;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.Coin;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.world.Dimension;
import com.project.paradoxplatformer.utils.world.coordinates.Coord2D;
import java.util.Optional;

public interface CoinFactory {
    
    Coin collectableCoin(final Coord2D entrypoint, final Dimension dimension, final Optional<TrajectoryInfo> trajStats, final PlayerModel player);

    //possible logic param
    Coin explodingCoin(final Coord2D entrypoint, final Dimension dimension, final Optional<TrajectoryInfo> trajStats);

    //world param or an obstacle/tile adder
    Coin platformCoin(final Coord2D entrypoint, final Dimension dimension, final Optional<TrajectoryInfo> trajStats);
}
