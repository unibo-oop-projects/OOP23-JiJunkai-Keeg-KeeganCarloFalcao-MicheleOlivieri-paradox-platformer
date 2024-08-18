package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public final class Coin extends AbstractHarmlessObstacle implements CollectableGameObject{

    protected Coin(Coord2D position, Dimension dimension) {
        super(position, dimension);
    }

    @Override
    public void getCollected(final PlayerModel player) {
        player.collectCoin();
    }
}
