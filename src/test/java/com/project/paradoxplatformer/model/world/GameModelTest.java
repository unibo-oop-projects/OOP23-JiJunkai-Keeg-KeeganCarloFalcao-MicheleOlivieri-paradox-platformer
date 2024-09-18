package com.project.paradoxplatformer.model.world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.paradoxplatformer.controller.deserialization.dtos.ColorDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.TrajMacro;
import com.project.paradoxplatformer.model.GameModelImpl;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.trigger.Trigger;
import com.project.paradoxplatformer.model.world.api.World;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Consumer;
import java.util.List;

/**
 * Unit tests for the {@link GameModelImpl} class.
 * <p>
 * This class contains test cases to verify the functionality of the
 * {@link GameModelImpl} class, including
 * initialization, rebuilding, actions on the world, and object removal.
 * </p>
 */
public final class GameModelTest {

    private static final int WORLD_WIDTH = 1000;
    private static final int WORLD_HEIGHT = 800;
    private static final int PLAYER_WIDTH = 30;
    private static final int PLAYER_HEIGHT = 30;
    private static final int OBSTACLE_WIDTH = 30;
    private static final int OBSTACLE_HEIGHT = 30;
    private static final int TRIGGER_WIDTH = 30;
    private static final int TRIGGER_HEIGHT = 30;
    private static final int PLAYER_POSITION = 0;
    private static final int OBSTACLE_POSITION = 100;
    private static final int TRIGGER_POSITION = 200;
    private static final int PLAYER_ID = 1;
    private static final int OBSTACLE_ID = 2;
    private static final int TRIGGER_ID = 3;

    private GameModelImpl platformModelData;
    private LevelDTO levelData;
    private GameDTO playerDTO;
    private GameDTO obstacleDTO;
    private GameDTO triggerDTO;

    /**
     * Sets up the test environment by initializing {@link LevelDTO} and
     * {@link GameModelImpl} instances.
     * This method creates sample {@link GameDTO} instances for player, obstacle,
     * and trigger, and uses them to
     * initialize the {@link GameModelImpl} instance.
     */
    @BeforeEach
    void setUp() {
        final ColorDTO color = new ColorDTO(); // Assuming ColorDTO has a constructor with a color name
        final TrajMacro[] emptyTraj = new TrajMacro[0]; // Assuming empty trajectory macro array

        this.playerDTO = new GameDTO(
                "player",
                PLAYER_ID,
                PLAYER_POSITION,
                PLAYER_POSITION,
                PLAYER_WIDTH,
                PLAYER_HEIGHT,
                null,
                null,
                color,
                emptyTraj,
                0);

        this.obstacleDTO = new GameDTO(
                "obstacle",
                OBSTACLE_ID,
                OBSTACLE_POSITION,
                OBSTACLE_POSITION,
                OBSTACLE_WIDTH,
                OBSTACLE_HEIGHT,
                "Coin",
                null,
                color,
                emptyTraj,
                -1);

        this.triggerDTO = new GameDTO(
                "trigger",
                TRIGGER_ID,
                TRIGGER_POSITION,
                TRIGGER_POSITION,
                TRIGGER_WIDTH,
                TRIGGER_HEIGHT,
                "Floor",
                null,
                color,
                emptyTraj,
                2);

        this.levelData = new LevelDTO(
                WORLD_WIDTH,
                WORLD_HEIGHT,
                new GameDTO[] { playerDTO, obstacleDTO, triggerDTO });

        platformModelData = new GameModelImpl(levelData);
    }

    /**
     * Tests the successful initialization of the game model.
     * <p>
     * This test verifies that the world is correctly initialized with the
     * dimensions, player, obstacle, and trigger
     * specified in the {@link LevelDTO}.
     * </p>
     */
    @Test
    void testInitSuccess() {
        platformModelData.init();

        World world = platformModelData.getWorld();

        assertEquals(WORLD_WIDTH, world.bounds().width(), "World width should be correctly initialized.");
        assertEquals(WORLD_HEIGHT, world.bounds().height(), "World height should be correctly initialized.");

        assertNotNull(world.player(), "Player should be added to the world.");
        assertEquals(PLAYER_ID, world.player().getID(), "Player ID should match.");

        List<Obstacle> obstacles = world.obstacles().stream().toList();
        assertEquals(1, obstacles.size(), "There should be one obstacle in the world.");
        assertEquals(OBSTACLE_ID, obstacles.get(0).getID(), "Obstacle ID should match.");

        List<Trigger> triggers = world.triggers().stream().toList();
        assertEquals(1, triggers.size(), "There should be one trigger in the world.");
        assertEquals(TRIGGER_ID, triggers.get(0).getID(), "Trigger ID should match.");
    }

    /**
     * Tests the rebuilding of the world.
     * <p>
     * This test verifies that the world can be successfully rebuilt and
     * re-initialized correctly.
     * </p>
     */
    @Test
    void testRebuildWorld() {
        platformModelData.init();

        platformModelData.rebuild();

        platformModelData.init();
        World world = platformModelData.getWorld();

        assertEquals(WORLD_WIDTH, world.bounds().width(), "World width should be correctly re-initialized.");
        assertEquals(WORLD_HEIGHT, world.bounds().height(), "World height should be correctly re-initialized.");
    }

    /**
     * Tests the removal of an obstacle from the world.
     * <p>
     * This test verifies that obstacles can be removed from the world and that the
     * removal is successful.
     * </p>
     */
    @Test
    void testActionOnWorld() {
        platformModelData.init();

        Consumer<World> checkPlayerExists = world -> assertNotNull(world.player(), "Player should exist in the world.");

        platformModelData.actionOnWorld(checkPlayerExists);
    }

    /**
     * Tests the removal of an obstacle from the world.
     * <p>
     * This test verifies that obstacles can be removed from the world and that the
     * removal is successful.
     * </p>
     */
    @Test
    void testRemoveObstacleFromTrigger() {
        platformModelData.init();

        World world = platformModelData.getWorld();

        List<Obstacle> obstacles = world.obstacles().stream().toList();
        assertTrue(obstacles.stream().anyMatch(o -> o.getID() == OBSTACLE_ID),
                "Obstacle should be present initially.");

        platformModelData.actionOnWorld(w -> w.removeGameObjects(obstacles.get(0)));

        assertTrue(world.obstacles().stream().noneMatch(o -> o.getID() == OBSTACLE_ID),
                "Obstacle should be removed from the world.");
    }
}
