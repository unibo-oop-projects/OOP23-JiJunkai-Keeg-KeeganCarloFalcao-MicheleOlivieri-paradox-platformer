package com.project.paradoxplatformer.model.world.api;

import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.Trigger;
import com.project.paradoxplatformer.utils.geometries.Dimension;

/**
 * A builder interface for constructing a game world in the game.
 * This interface provides methods to add various components to the world and
 * then build it.
 */
public interface WorldBuilder {

    /**
     * Adds a player model to the world being built.
     *
     * @param playerModel The player model to be added.
     * @return The current instance of WorldBuilder for chaining.
     */
    WorldBuilder addPlayer(PlayerModel playerModel);

    /**
     * Adds one or more triggers to the world being built.
     *
     * @param trigger One or more triggers to be added.
     * @return The current instance of WorldBuilder for chaining.
     */
    WorldBuilder addTrigger(Trigger... trigger);

    /**
     * Adds one or more obstacles to the world being built.
     *
     * @param obstacle One or more obstacles to be added.
     * @return The current instance of WorldBuilder for chaining.
     */
    WorldBuilder addObstacle(Obstacle... obstacle);

    /**
     * Sets the dimensions (bounds) of the world being built.
     *
     * @param dimension The dimensions of the world.
     * @return The current instance of WorldBuilder for chaining.
     */
    WorldBuilder addBounds(Dimension dimension);

    /**
     * Builds and returns the constructed world with the added components.
     *
     * @return The constructed World instance.
     */
    World build();
}
