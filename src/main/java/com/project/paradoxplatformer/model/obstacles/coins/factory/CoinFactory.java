package com.project.paradoxplatformer.model.obstacles.coins.factory;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.Coin;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import java.util.Queue;

public interface CoinFactory {
    
    Coin collectableCoin(final Coord2D entrypoint, final Dimension dimension, final Queue<TrajectoryInfo> trajStats, final PlayerModel player);

    //possible logic param
    Coin explodingCoin(final Coord2D entrypoint, final Dimension dimension, final Queue<TrajectoryInfo> trajStats);

    //world param or an obstacle/tile adder
    Coin platformCoin(final Coord2D entrypoint, final Dimension dimension, final Queue<TrajectoryInfo> trajStats);
}
