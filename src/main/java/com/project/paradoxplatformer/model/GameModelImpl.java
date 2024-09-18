package com.project.paradoxplatformer.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import static java.util.function.Predicate.not;

import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.model.mappings.model.ModelMappingFactory;
import com.project.paradoxplatformer.model.mappings.model.ModelMappingFactoryImpl;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.Trigger;
import com.project.paradoxplatformer.model.trigger.Triggerable;
import com.project.paradoxplatformer.model.world.WordBuilderImpl;
import com.project.paradoxplatformer.model.world.WorldImpl;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.model.world.api.WorldBuilder;
import com.project.paradoxplatformer.utils.geometries.Dimension;

/**
 * Represents the model data for the platform game.
 * <p>
 * This class initializes and manages the game world, including player,
 * obstacles, and triggers.
 * </p>
 */
public final class GameModelImpl implements GameModel {

        private final LevelDTO packedData;
        private WorldBuilder worldBuilder;
        private World world;
        private final ModelMappingFactory modelFactory;

        /**
         * Constructs a {@link GameModelImpl} with the specified level data.
         * 
         * @param packedData the level data to initialize the game model
         */
        public GameModelImpl(final LevelDTO packedData) {
                this.packedData = packedData;
                this.modelFactory = new ModelMappingFactoryImpl();
                this.worldBuilder = new WordBuilderImpl();
        }

        /**
         * Initializes the game model based on the provided level data.
         * <p>
         * This method maps the player, obstacles, and triggers from the level data and
         * builds the game world.
         * </p>
         * 
         * @throws IllegalStateException    if the attribute type of game DTO is
         *                                  undefined
         * @throws IllegalArgumentException if no game DTOs match the required
         *                                  attributes
         */
        @Override
        public void init() {
                Optional.of(
                                Arrays.stream(packedData.getGameDTOs())
                                                .map(GameDTO::getType)
                                                .anyMatch(Objects::isNull))
                                .filter(not(Boolean::booleanValue))
                                .orElseThrow(() -> new IllegalStateException(
                                                "Can not map, attribute GameDTO is undefined"));

                final PlayerModel player = modelFactory.playerToModel()
                                .map(
                                                this.findGameDTOData("player")
                                                                .stream()
                                                                .findFirst()
                                                                .orElseThrow());

                final Obstacle[] obstacles = this.findGameDTOData("obstacle").stream()
                                .map(modelFactory.obstacleToModel()::map)
                                .toList()
                                .toArray(new Obstacle[0]);

                final Trigger[] triggers = this.findGameDTOData("trigger").stream()
                                .map(modelFactory.triggerToModel()::map)
                                .toList()
                                .toArray(new Trigger[0]);

                this.addObstaclesToTrigger(obstacles, triggers);

                this.world = this.worldBuilder
                                .addBounds(new Dimension(packedData.getWidth(), packedData.getHeight()))
                                .addPlayer(player)
                                .addObstacle(obstacles)
                                .addTrigger(triggers)
                                .build();
        }

        /**
         * Returns a defensive copy of the current game world.
         * <p>
         * Due to security reasons, the returned world is a copy of the internal world
         * state.
         * </p>
         * 
         * @return a defensive copy of the current game world
         */
        @Override
        public World getWorld() {
                return new WorldImpl(this.world);
        }

        /**
         * Rebuilds the game world using a new world builder.
         */
        @Override
        public void rebuild() {
                this.worldBuilder = new WordBuilderImpl();
        }

        /**
         * Executes the specified action on the current game world.
         * 
         * @param action the action to be executed on the game world
         */
        @Override
        public void actionOnWorld(final Consumer<World> action) {
                action.accept(this.world);
        }

        /**
         * Finds and returns the collection of {@link GameDTO} objects matching the
         * specified attribute.
         * 
         * @param attribute the type of game DTO to find
         * @return a collection of {@link GameDTO} objects matching the specified
         *         attribute
         * @throws IllegalArgumentException if no game DTOs match the specified
         *                                  attribute
         */
        private Collection<GameDTO> findGameDTOData(final String attribute) {
                return Optional.of(
                                List.of(packedData.getGameDTOs())
                                                .stream()
                                                .filter(g -> g.getType().equals(attribute))
                                                .toList())
                                .filter(not(List::isEmpty))
                                .orElseThrow(
                                                () -> new IllegalArgumentException(
                                                                "attribute does not match any game dto type: "
                                                                                + attribute));
        }

        private void addObstaclesToTrigger(final Obstacle[] obstacles, final Trigger[] triggers) {
                Arrays.stream(triggers)
                                .filter(t -> t.getTriggerableID().isPresent())
                                .forEach(t -> t.addObstacle(this.findObstacle(t.getTriggerableID().get(), obstacles)));
        }

        private Triggerable findObstacle(final int id, final Obstacle[] obstacles) {
                return Arrays.stream(obstacles)
                                .filter(o -> o.getID() == id)
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException(
                                                "Associated obstacle id not found in the stream of obstacles"));
        }
}
