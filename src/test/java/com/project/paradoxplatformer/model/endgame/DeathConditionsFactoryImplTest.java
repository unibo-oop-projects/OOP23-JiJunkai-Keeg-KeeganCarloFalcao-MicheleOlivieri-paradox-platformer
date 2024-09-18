package com.project.paradoxplatformer.model.endgame;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.model.endgame.condition.DeathObstacleCollisionCondition;
import com.project.paradoxplatformer.model.endgame.condition.FallenCondition;
import com.project.paradoxplatformer.model.endgame.condition.OutOfMapCondition;
import com.project.paradoxplatformer.model.endgame.condition.TimeLimitDeathCondition;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 *
 * @author michele
 */
public class DeathConditionsFactoryImplTest {

    private DeathConditionsFactoryImpl factory;
    private PlayerModel player;

    @BeforeEach
    public void setUp() {
        factory = new DeathConditionsFactoryImpl();
        player = new PlayerModel(1, new Coord2D(0, 0), new Dimension(10, 20));
    }

    @Test
    public void testCreateConditionsForLevel() {
        System.out.println("createConditionsForLevel");
        Iterator<DeathCondition> result = factory.createConditionsForLevel(Level.LEVEL_ONE, player);
        List<DeathCondition> expResult = Arrays.asList(new FallenCondition(player), new DeathObstacleCollisionCondition());
        assertIteratorEquals(expResult.iterator(), result);
    }

    @Test
    public void testDefaultConditions() {
        System.out.println("defaultConditions");
        Iterator<DeathCondition> result = factory.defaultConditions();
        assertFalse(result.hasNext(), "Default conditions should be empty.");
    }

    @Test
    public void testLevelOneConditions() {
        System.out.println("levelOneConditions");
        Iterator<DeathCondition> result = factory.levelOneConditions();
        List<DeathCondition> expResult = Arrays.asList(new FallenCondition(player), new DeathObstacleCollisionCondition());
        assertIteratorEquals(expResult.iterator(), result);
    }

    /**
     * Test of levelTwoConditions method, of class DeathConditionsFactoryImpl.
     */
    @Test
    public void testLevelTwoConditions() {
        System.out.println("levelTwoConditions");
        Iterator<DeathCondition> result = factory.levelTwoConditions();
        List<DeathCondition> expResult = Arrays.asList(new TimeLimitDeathCondition(600));
        assertIteratorEquals(expResult.iterator(), result);
    }

    @Test
    public void testLevelThreeConditions() {
        System.out.println("levelThreeConditions");
        Iterator<DeathCondition> result = factory.levelThreeConditions();
        List<DeathCondition> expResult = Arrays.asList(new FallenCondition(player), new DeathObstacleCollisionCondition(), new OutOfMapCondition(player));
        assertIteratorEquals(expResult.iterator(), result);
    }

    @Test
    public void testLevelFourConditions() {
        System.out.println("levelFourConditions");
        Iterator<DeathCondition> result = factory.levelFourConditions();
        List<DeathCondition> expResult = Arrays.asList(new FallenCondition(player), new DeathObstacleCollisionCondition());
        assertIteratorEquals(expResult.iterator(), result);
    }

    /**
     * Helper method to compare two iterators.
     */
    private <T> void assertIteratorEquals(Iterator<T> expected, Iterator<T> actual) {
        while (expected.hasNext() && actual.hasNext()) {
            assertEquals(expected.next(), actual.next(), "Iterator elements differ.");
        }
        assertFalse(expected.hasNext(), "Expected iterator has more elements.");
        assertFalse(actual.hasNext(), "Actual iterator has more elements.");
    }
}
