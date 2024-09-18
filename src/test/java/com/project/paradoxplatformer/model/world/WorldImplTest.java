package com.project.paradoxplatformer.model.world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.obstacles.DeathCoin;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.obstacles.Saw;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.Floor;
import com.project.paradoxplatformer.model.trigger.Trigger;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

/**
 * Unit tests for the {@link WorldImpl} class, validating the management and
 * retrieval of world elements such as obstacles, triggers, and the player
 * model.
 * <p>
 * Tests include:
 * <ul>
 * <li>Validation of obstacle and trigger collections as unmodifiable.</li>
 * <li>Ensuring proper access and removal of game objects.</li>
 * <li>Verifying the retrieval of all world elements through gameObjects().</li>
 * <li>Testing the correct retrieval of the player model.</li>
 * </ul>
 */
public final class WorldImplTest {

    private static final int PLAYER_WIDTH = 30;
    private static final int PLAYER_HEIGHT = 30;
    private static final int WORLD_WIDTH = 1000;
    private static final int WORLD_HEIGHT = 800;
    private static final int SAW_ID = 2;
    private static final int FLOOR_TRIGGER_ID = 1;
    private static final Coord2D ORIGIN = Coord2D.origin();
    private static final int OTHER_OBJECT_X = 20;
    private static final int OTHER_OBJECT_Y = 22;

    private WorldImpl world;
    private PlayerModel playeTest;
    private Obstacle sawTest;
    private Trigger floorTrigger;
    private Dimension worldBounds;
    private Dimension mockDimension;

    @BeforeEach
    void setUp() {
        // Mocking player, obstacle, trigger, and world dimension
        this.playeTest = new PlayerModel();
        this.mockDimension = new Dimension(PLAYER_WIDTH, PLAYER_HEIGHT);
        this.sawTest = new Saw(SAW_ID, ORIGIN, mockDimension, null);
        this.worldBounds = new Dimension(WORLD_WIDTH, WORLD_HEIGHT); // World dimensions
        this.floorTrigger = new Floor(FLOOR_TRIGGER_ID, new Coord2D(2, 0), mockDimension, null);

        // Initialize world with mocked objects
        world = new WorldImpl(
                List.of(sawTest), // Single obstacle
                List.of(floorTrigger), // Single trigger
                playeTest,
                worldBounds);
    }

    @Test
    void testObstacles() {
        // Verify that the obstacles are properly returned
        assertTrue(world.obstacles().contains(sawTest), "World should contain the obstacle.");
        assertThrows(UnsupportedOperationException.class, () -> {
            world.obstacles().add(sawTest);
        }, "Obstacles collection should be unmodifiable.");
    }

    @Test
    void testTriggers() {
        // Verify that the triggers are properly returned
        assertTrue(world.triggers().contains(floorTrigger), "World should contain the trigger.");
        assertThrows(UnsupportedOperationException.class, () -> {
            world.triggers().add(floorTrigger); // Collection is unmodifiable
        }, "Triggers collection should be unmodifiable.");
    }

    @Test
    void testPlayer() {
        // Verify that the player is accessible
        assertEquals(playeTest, world.player(), "World should return the correct player.");
    }

    @Test
    void testRemoveGameObjects() {
        // Test removing an obstacle
        assertTrue(world.removeGameObjects(sawTest), "Removing an obstacle should return true.");
        assertFalse(world.obstacles().contains(sawTest), "Obstacle should be removed from the world.");

        // Test removing a trigger
        assertTrue(world.removeGameObjects(floorTrigger), "Removing a trigger should return true.");
        assertFalse(world.triggers().contains(floorTrigger), "Trigger should be removed from the world.");

        // Test removing a non-existing object
        MutableObject mockOtherObject = new DeathCoin(0, new Coord2D(OTHER_OBJECT_X, OTHER_OBJECT_Y), mockDimension,
                null);
        assertFalse(world.removeGameObjects(mockOtherObject), "Removing a non-existing object should return false.");
    }

    @Test
    void testGameObjects() {
        // Verify that gameObjects returns all obstacles, triggers, and the player
        var gameObjects = world.gameObjects();
        assertTrue(gameObjects.contains(sawTest), "Game objects should include the obstacle.");
        assertTrue(gameObjects.contains(floorTrigger), "Game objects should include the trigger.");
        assertTrue(gameObjects.contains(playeTest), "Game objects should include the player.");
    }
}
