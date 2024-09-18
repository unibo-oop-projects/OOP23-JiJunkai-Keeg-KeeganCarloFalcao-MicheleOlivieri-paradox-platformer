/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

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
 * Verifies the victory condition based on collecting a specific number of coins.
 * 
 * </p>
 * * @author Michele Olivieri
 */
public class CoinCollectionVictoryConditionTest {

    private final static int COIN = 5;

    /**
     * Default constructor for the test class.
     */
    public CoinCollectionVictoryConditionTest() {
    }

    /**
     * Test of win method, of class CoinCollectionVictoryCondition.
     */
    @Test
    public void testWin() {
        System.out.println("win");
        final PlayerModel player = new PlayerModel();
        player.collectItem(new Coin(COIN, new Coord2D(0,0), new Dimension(COIN, COIN)));
        final CoinCollectionVictoryCondition instance = new CoinCollectionVictoryCondition(player, CoinCollectionVictoryConditionTest.COIN);
        final boolean expResult = false;
        final boolean result = instance.win();
        assertEquals(expResult, result);
    }
}
