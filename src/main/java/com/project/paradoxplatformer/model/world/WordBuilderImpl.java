package com.project.paradoxplatformer.model.world;

import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.Trigger;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.model.world.api.WorldBuilder;
import com.project.paradoxplatformer.utils.SecureWrapper;
import com.project.paradoxplatformer.utils.geometries.Dimension;

import java.util.List;
import java.util.ArrayList;

/**
 * Implementation of the {@link WorldBuilder} interface that constructs
 * a {@link World} by adding obstacles, triggers, a player, and bounds.
 * <p>
 * This class provides methods to configure and build a world with various
 * game elements. It ensures that a world can only be built once.
 * </p>
 */
public final class WordBuilderImpl implements WorldBuilder {

    private final List<Trigger> triggers;
    private final List<Obstacle> obstacles;
    private SecureWrapper<PlayerModel> player;
    private Dimension bounds;
    private boolean isBuild;

    /**
     * Constructs a new {@code WordBuilderImpl} instance.
     * Initializes empty lists for triggers and obstacles, and sets the
     * initial state to not built.
     */
    public WordBuilderImpl() {
        this.obstacles = new ArrayList<>();
        this.triggers = new ArrayList<>();
        this.isBuild = false;
        this.player = null;
        // SHOULD FIX CAUSE GAME CANNOt builD WITHOUT PLAYER
    }

    /**
     * Adds a player to the world being built.
     * 
     * @param playerModel the player model to be added
     * @return the current instance of {@code WorldBuilder}
     * @throws IllegalStateException if the world has already been built
     */
    @Override
    public WorldBuilder addPlayer(final PlayerModel playerModel) {
        buildCheck();
        this.player = SecureWrapper.of(playerModel);
        return this;
    }

    /**
     * Adds one or more triggers to the world being built.
     * 
     * @param trigger the triggers to be added
     * @return the current instance of {@code WorldBuilder}
     * @throws IllegalStateException if the world has already been built
     */
    @Override
    public WorldBuilder addTrigger(final Trigger... trigger) {
        buildCheck();
        this.triggers.addAll(List.of(trigger));
        return this;
    }

    /**
     * Adds one or more obstacles to the world being built.
     * 
     * @param obstacle the obstacles to be added
     * @return the current instance of {@code WorldBuilder}
     * @throws IllegalStateException if the world has already been built
     */
    @Override
    public WorldBuilder addObstacle(final Obstacle... obstacle) {
        buildCheck();
        this.obstacles.addAll(List.of(obstacle));
        return this;
    }

    /**
     * Sets the bounds for the world being built.
     * 
     * @param dimension the dimensions representing the bounds of the world
     * @return the current instance of {@code WorldBuilder}
     * @throws IllegalStateException if the world has already been built
     */
    @Override
    public WorldBuilder addBounds(final Dimension dimension) {
        buildCheck();
        this.bounds = dimension;
        return this;
    }

    /**
     * Builds and returns the world using the configured elements.
     * 
     * @return a new instance of {@link World} with the configured elements
     * @throws IllegalStateException if the world has already been built
     */
    @Override
    public World build() {
        buildCheck();
        this.isBuild = true;
        return new WorldImpl(obstacles, triggers, player.get(), bounds);
    }

    /**
     * Checks if the world has already been built and throws an exception
     * if an attempt is made to build it again.
     * 
     * @throws IllegalStateException if the world has already been built
     */
    private void buildCheck() {
        if (this.isBuild) {
            throw new IllegalStateException("World is already built, cannot rebuild!");
        }
    }

}
