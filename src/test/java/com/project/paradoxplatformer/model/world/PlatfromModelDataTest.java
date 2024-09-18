package com.project.paradoxplatformer.model.world;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.Trigger;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.utils.geometries.Dimension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Consumer;
import java.util.List;

// class PlatfromModelDataTest {

//     private PlatfromModelData platformModelData;
//     private LevelDTO levelData;
//     private GameDTO playerDTO;
//     private GameDTO obstacleDTO;
//     private GameDTO triggerDTO;

//     @BeforeEach
//     void setUp() {
//         // Initialize LevelDTO with player, obstacles, and trigger data
//         playerDTO = new GameDTO("player", 1, new Dimension(30, 30), 0, 0);  // Player at (0, 0)
//         obstacleDTO = new GameDTO("obstacle", 2, new Dimension(30, 30), 100, 100);  // Obstacle at (100, 100)
//         triggerDTO = new GameDTO("trigger", 3, new Dimension(30, 30), 200, 200);  // Trigger at (200, 200)

//         levelData = new LevelDTO(
//                 1000, 800,  // World bounds
//                 new GameDTO[]{playerDTO, obstacleDTO, triggerDTO}  // Game DTOs (player, obstacle, trigger)
//         );

//         // Initialize PlatformModelData with the created LevelDTO
//         platformModelData = new PlatfromModelData(levelData);
//     }

//     @Test
//     void testInitSuccess() {
//         // Initialize the game model
//         platformModelData.init();

//         // Retrieve the built world
//         World world = platformModelData.getWorld();

//         // Verify the world dimensions
//         assertEquals(1000, world.bounds().width(), "World width should be correctly initialized.");
//         assertEquals(800, world.bounds().height(), "World height should be correctly initialized.");

//         // Verify that the player was added
//         assertNotNull(world.player(), "Player should be added to the world.");
//         assertEquals(playerDTO.getID(), world.player().getID(), "Player ID should match.");

//         // Verify that the obstacle was added
//         List<Obstacle> obstacles = world.obstacles();
//         assertEquals(1, obstacles.size(), "There should be one obstacle in the world.");
//         assertEquals(obstacleDTO.getID(), obstacles.get(0).getID(), "Obstacle ID should match.");

//         // Verify that the trigger was added
//         List<Trigger> triggers = world.triggers();
//         assertEquals(1, triggers.size(), "There should be one trigger in the world.");
//         assertEquals(triggerDTO.getID(), triggers.get(0).getID(), "Trigger ID should match.");
//     }

//     @Test
//     void testInitFailureOnUndefinedGameDTO() {
//         // Create an invalid LevelDTO with null GameDTO type
//         GameDTO invalidDTO = new GameDTO(null, 1, new Dimension(30, 30), 0, 0);
//         LevelDTO invalidLevelData = new LevelDTO(1000, 800, new GameDTO[]{invalidDTO});

//         PlatfromModelData invalidPlatformModelData = new PlatfromModelData(invalidLevelData);

//         // Expect an IllegalStateException due to undefined GameDTO type
//         assertThrows(IllegalStateException.class, invalidPlatformModelData::init, 
//                      "Should throw an exception when GameDTO type is undefined.");
//     }

//     @Test
//     void testRebuildWorld() {
//         // Initialize the game model
//         platformModelData.init();

//         // Rebuild the world
//         platformModelData.rebuild();

//         // Initialize again after rebuilding
//         platformModelData.init();
//         World world = platformModelData.getWorld();

//         // Verify that the world was successfully rebuilt
//         assertEquals(1000, world.bounds().width(), "World width should be correctly re-initialized.");
//         assertEquals(800, world.bounds().height(), "World height should be correctly re-initialized.");
//     }

//     @Test
//     void testActionOnWorld() {
//         // Initialize the game model
//         platformModelData.init();

//         // Define a consumer that checks if the player exists
//         Consumer<World> checkPlayerExists = world -> assertNotNull(world.player(), "Player should exist in the world.");

//         // Perform action on the world
//         platformModelData.actionOnWorld(checkPlayerExists);
//     }

//     @Test
//     void testFindGameDTODataThrowsForInvalidAttribute() {
//         // Expect IllegalArgumentException when trying to find a non-existent attribute
//         assertThrows(IllegalArgumentException.class, () -> {
//             platformModelData.init();
//             platformModelData.findGameDTOData("non-existent");
//         }, "Should throw an exception for non-existent attribute.");
//     }

//     @Test
//     void testRemoveObstacleFromTrigger() {
//         // Initialize the game model
//         platformModelData.init();

//         // Retrieve the built world
//         World world = platformModelData.getWorld();

//         // Check if the obstacle is present initially
//         List<Obstacle> obstacles = world.obstacles();
//         assertTrue(obstacles.stream().anyMatch(o -> o.getID() == obstacleDTO.getID()), 
//                    "Obstacle should be present initially.");

//         // Remove obstacle
//         Trigger trigger = world.triggers().get(0);
//         platformModelData.actionOnWorld(w -> w.removeGameObjects(obstacles.get(0)));

//         // Verify obstacle is removed
//         assertFalse(world.obstacles().stream().anyMatch(o -> o.getID() == obstacleDTO.getID()), 
//                     "Obstacle should be removed from the world.");
//     }
// }

