package com.project.paradoxplatformer.model.endgame.condition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.project.paradoxplatformer.model.obstacles.Coin;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * Test class for the CoinCollectionVictoryCondition.
 * <p>
 * Verifies the victory condition based on collecting a specific number of
 * coins.
 * </p>
 * 
 * @author Michele Olivieri
 */
class CoinCollectionVictoryConditionTest {

    private static final int COIN = 5;

    /**
     * Test of win method, of class CoinCollectionVictoryCondition.
     */
    @Test
    void testWin() {
        final PlayerModel player = new PlayerModel();
        player.collectItem(new Coin(COIN, new Coord2D(0, 0), new Dimension(COIN, COIN)));
        final CoinCollectionVictoryCondition instance = new CoinCollectionVictoryCondition(player, COIN);
        final boolean expResult = false; // Adjust expected result as per test scenario.
        final boolean result = instance.win();
        assertEquals(expResult, result);
    }
}
