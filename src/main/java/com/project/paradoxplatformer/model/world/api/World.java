package com.project.paradoxplatformer.model.world.api;

import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.Trigger;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import java.util.Collection;

/**
 * An interface representing the game world in the Paradox Platformer game.
 * This interface provides methods to interact with various elements within the
 * game world.
 */
public interface World {

    /**
     * Retrieves a collection of all obstacles present in the world.
     *
     * @return A collection of obstacles.
     */
    Collection<Obstacle> obstacles();

    /**
     * Retrieves a collection of all triggers present in the world.
     *
     * @return A collection of triggers.
     */
    Collection<Trigger> triggers();

    /**
     * Retrieves a collection of all mutable objects present in the world.
     * Mutable objects are entities that can change their state or properties during
     * gameplay.
     *
     * @return A collection of mutable objects.
     */
    Collection<MutableObject> gameObjects();

    /**
     * Removes a specified mutable object from the world.
     *
     * @param mutGameObject The mutable object to be removed.
     * @return true if the object was successfully removed; false otherwise.
     */
    boolean removeGameObjects(MutableObject mutGameObject);

    /**
     * Retrieves the player model representing the player in the world.
     *
     * @return The player model.
     */
    PlayerModel player();

    /**
     * Retrieves the dimensions of the world, representing its boundaries.
     *
     * @return The dimension of the world.
     */
    Dimension bounds();
}
