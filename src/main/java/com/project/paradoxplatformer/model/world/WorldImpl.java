package com.project.paradoxplatformer.model.world;

import java.util.*;

import com.google.common.collect.Sets;
import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.Trigger;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.utils.SecureWrapper;
import com.project.paradoxplatformer.utils.geometries.Dimension;

/**
 * Implementation of the World interface, representing the game world
 * in the Paradox Platformer game. This class maintains collections of
 * obstacles, triggers, and provides access to the player model and world
 * dimensions.
 */
public final class WorldImpl implements World {

    private final Set<Obstacle> obstacles;
    private final Set<Trigger> triggers;
    private final SecureWrapper<PlayerModel> player;
    private final Dimension bounds;

    /**
     * Constructs a WorldImpl instance with specified obstacles, triggers, player
     * model, and world dimensions.
     *
     * @param obstacles The collection of obstacles in the world.
     * @param triggers  The collection of triggers in the world.
     * @param player    The player model representing the player in the world.
     * @param bounds    The dimensions (bounds) of the world.
     */
    public WorldImpl(final Collection<Obstacle> obstacles, final Collection<Trigger> triggers, final PlayerModel player,
            final Dimension bounds) {
        this.obstacles = new LinkedHashSet<>(obstacles);
        this.triggers = new LinkedHashSet<>(triggers);
        this.player = SecureWrapper.of(player);
        this.bounds = bounds;
    }

    /**
     * Copy constructor to create a new WorldImpl instance from an existing World.
     *
     * @param copy The World instance to copy.
     */
    public WorldImpl(final World copy) {
        this(copy.obstacles(),
                copy.triggers(),
                copy.player(),
                copy.bounds());
    }

    @Override
    public Collection<Obstacle> obstacles() {
        return Collections.unmodifiableCollection(this.obstacles);
    }

    @Override
    public Collection<Trigger> triggers() {
        return Collections.unmodifiableCollection(this.triggers);
    }

    /**
     * Retrieves the player model in the world.
     * The player model is accessed securely.
     *
     * @return The player model.
     */
    @Override
    public PlayerModel player() {
        return this.player.get();
    }

    @Override
    public Dimension bounds() {
        return this.bounds;
    }

    /**
     * Removes a mutable object from the world. The object can be either a trigger
     * or an obstacle.
     *
     * @param mutableGameObj The mutable object to remove.
     * @return true if the object was successfully removed; false otherwise.
     */
    @Override
    public boolean removeGameObjects(final MutableObject mutableGameObj) {
        if (mutableGameObj instanceof Trigger) {
            return this.triggers.remove(mutableGameObj);
        } else if (mutableGameObj instanceof Obstacle) {
            return this.obstacles.remove(mutableGameObj);
        }
        return false;
    }

    /**
     * Retrieves a collection of all mutable objects in the world, including
     * obstacles, triggers, and the player model.
     *
     * @return A collection of mutable objects.
     */
    @Override
    public Collection<MutableObject> gameObjects() {
        return Sets.union(Sets.union(this.obstacles, this.triggers), Set.of(this.player()));
    }
}
