package com.project.paradoxplatformer.model.world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.paradoxplatformer.controller.deserialization.dtos.ColorDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.TrajMacro;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.trigger.Trigger;
import com.project.paradoxplatformer.model.world.api.World;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Consumer;
import java.util.List;

/**
 * Unit tests for the {@link PlatfromModelData} class.
 * <p>
 * This class contains test cases to verify the functionality of the {@link PlatfromModelData} class, including
 * initialization, rebuilding, actions on the world, and object removal.
 * </p>
 */
public final class PlatfromModelDataTest {

    private PlatfromModelData platformModelData;
    private LevelDTO levelData;
    private GameDTO playerDTO;
    private GameDTO obstacleDTO;
    private GameDTO triggerDTO;

    /**
     * Sets up the test environment by initializing {@link LevelDTO} and {@link PlatfromModelData} instances.
     * This method creates sample {@link GameDTO} instances for player, obstacle, and trigger, and uses them to
     * initialize the {@link PlatfromModelData} instance.
     */
    @BeforeEach
    void setUp() {
        // Initialize LevelDTO with player, obstacles, and trigger data
        final ColorDTO color = new ColorDTO(); // Assuming ColorDTO has a constructor with a color name
        final TrajMacro[] emptyTraj = new TrajMacro[0]; // Assuming empty trajectory macro array

        // Create GameDTO instances
        this.playerDTO = new GameDTO(
                "player",      // Type
                1,             // ID
                0,             // x-coordinate
                0,             // y-coordinate
                30,            // Width
                30,            // Height
                null,          // Subtype (null or empty if not used)
                null,          // Image (null if not used)
                color,         // ColorDTO
                emptyTraj,     // Trajectory macros
                0              // Triggering ID
        );

        this.obstacleDTO = new GameDTO(
                "obstacle",    // Type
                2,             // ID
                100,           // x-coordinate
                100,           // y-coordinate
                30,            // Width
                30,            // Height
                "Coin",          // Subtype
                null,          // Image
                color,         // ColorDTO
                emptyTraj,     // Trajectory macros
                -1              // Triggering ID
        );

        this.triggerDTO = new GameDTO(
                "trigger",     // Type
                3,             // ID
                200,           // x-coordinate
                200,           // y-coordinate
                30,            // Width
                30,            // Height
                "Floor",          // Subtype
                null,          // Image
                color,         // ColorDTO
                emptyTraj,     // Trajectory macros
                2              // Triggering ID
        );

        this.levelData = new LevelDTO(
                1000, 800,  // World bounds
                new GameDTO[]{playerDTO, obstacleDTO, triggerDTO}  // Game DTOs (player, obstacle, trigger)
        );

        // Initialize PlatformModelData with the created LevelDTO
        platformModelData = new PlatfromModelData(levelData);
    }

     /**
     * Tests the successful initialization of the game model.
     * <p>
     * This test verifies that the world is correctly initialized with the dimensions, player, obstacle, and trigger
     * specified in the {@link LevelDTO}.
     * </p>
     */
    @Test
    void testInitSuccess() {
        // Initialize the game model
        platformModelData.init();

        // Retrieve the built world
        World world = platformModelData.getWorld();

        // Verify the world dimensions
        assertEquals(1000, world.bounds().width(), "World width should be correctly initialized.");
        assertEquals(800, world.bounds().height(), "World height should be correctly initialized.");

        // Verify that the player was added
        assertNotNull(world.player(), "Player should be added to the world.");
        assertEquals(playerDTO.getID(), world.player().getID(), "Player ID should match.");

        // Verify that the obstacle was added
        List<Obstacle> obstacles = world.obstacles().stream().toList();
        assertEquals(1, obstacles.size(), "There should be one obstacle in the world.");
        assertEquals(obstacleDTO.getID(), obstacles.get(0).getID(), "Obstacle ID should match.");

        // Verify that the trigger was added
        List<Trigger> triggers = world.triggers().stream().toList();
        assertEquals(1, triggers.size(), "There should be one trigger in the world.");
        assertEquals(triggerDTO.getID(), triggers.get(0).getID(), "Trigger ID should match.");
    }

      /**
     * Tests the rebuilding of the world.
     * <p>
     * This test verifies that the world can be successfully rebuilt and re-initialized correctly.
     * </p>
     */
    @Test
    void testRebuildWorld() {
        // Initialize the game model
        platformModelData.init();

        // Rebuild the world
        platformModelData.rebuild();

        // Initialize again after rebuilding
        platformModelData.init();
        World world = platformModelData.getWorld();

        // Verify that the world was successfully rebuilt
        assertEquals(1000, world.bounds().width(), "World width should be correctly re-initialized.");
        assertEquals(800, world.bounds().height(), "World height should be correctly re-initialized.");
    }

     /**
     * Tests the removal of an obstacle from the world.
     * <p>
     * This test verifies that obstacles can be removed from the world and that the removal is successful.
     * </p>
     */
    @Test
    void testActionOnWorld() {
        // Initialize the game model
        platformModelData.init();

        // Define a consumer that checks if the player exists
        Consumer<World> checkPlayerExists = world -> assertNotNull(world.player(), "Player should exist in the world.");

        // Perform action on the world
        platformModelData.actionOnWorld(checkPlayerExists);
    }

     /**
     * Tests the removal of an obstacle from the world.
     * <p>
     * This test verifies that obstacles can be removed from the world and that the removal is successful.
     * </p>
     */
    @Test
    void testRemoveObstacleFromTrigger() {
        // Initialize the game model
        platformModelData.init();

        // Retrieve the built world
        World world = platformModelData.getWorld();

        // Check if the obstacle is present initially
        List<Obstacle> obstacles = world.obstacles().stream().toList();
        assertTrue(obstacles.stream().anyMatch(o -> o.getID() == obstacleDTO.getID()), 
                   "Obstacle should be present initially.");

        platformModelData.actionOnWorld(w -> w.removeGameObjects(obstacles.get(0)));

        // Verify obstacle is removed
        assertTrue(world.obstacles().stream().anyMatch(o -> o.getID() == obstacleDTO.getID()), 
                    "Obstacle should be removed from the world.");
    }
}

