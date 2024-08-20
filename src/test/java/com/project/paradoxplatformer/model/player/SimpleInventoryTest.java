package com.project.paradoxplatformer.model.player;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;
import com.project.paradoxplatformer.model.obstacles.Coin;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public class SimpleInventoryTest {
    @Test
    void testGetItemsCounts() {

        PlayerModel player = new PlayerModel(Coord2D.origin(), Dimension.dot());
        CollectableGameObject coin = new Coin(Coord2D.origin(), Dimension.dot());
        player.collectItem(coin);
        player.collectItem(coin);

        System.out.println(player.getInventoryData());
        assertTrue(player.getInventoryData().get(Coin.class.getSimpleName()) == 2L);
        
    }
}
