package com.project.paradoxplatformer.model;

import java.util.function.Consumer;

import com.project.paradoxplatformer.model.world.api.World;

/**
 * Interface representing the data and operations related to the game model.
 * Provides methods to initialize the model, access the world, rebuild the
 * model,
 * and perform actions on the world.
 */
public interface GameModelData {

    /**
     * Initializes the game model. This method should set up any necessary
     * state or resources required for the model to function.
     */
    void init();

    /**
     * Retrieves the current world associated with the game model.
     *
     * @return The current {@link World} instance.
     */
    World getWorld();

    /**
     * Rebuilds or resets the game model. This method should handle any
     * reinitialization or reconstruction of the model's state.
     */
    void rebuild();

    /**
     * Performs a specified action on the world. The action is defined by a
     * {@link Consumer} that takes the {@link World} instance as input.
     *
     * @param action A {@link Consumer} that defines the action to be performed
     *               on the world.
     */
    void actionOnWorld(Consumer<World> action);
}
