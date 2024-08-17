package com.project.paradoxplatformer.model.world;

import java.util.*;

import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.utils.SecureWrapper;
import com.project.paradoxplatformer.utils.collision.CollisionManager;
import com.project.paradoxplatformer.utils.geometries.Dimension;

public final class WorldImpl implements World {

    private final Set<Obstacle> obstacles;
    private final Set<Trigger> triggers;
    private final SecureWrapper<PlayerModel> player;
    private final Dimension bounds;
    private final CollisionManager collisionManager;

    public WorldImpl(final Collection<Obstacle> obstacles, final Collection<Trigger> triggers, final PlayerModel player,
            final Dimension bounds, CollisionManager collisionManager) {
        this.obstacles = new LinkedHashSet<>(obstacles);
        this.triggers = new LinkedHashSet<>(triggers);
        this.player = SecureWrapper.of(player);
        this.bounds = bounds;
        this.collisionManager = collisionManager;
    }

    public WorldImpl(final World copy) {
        this(copy.obstacles(), 
            copy.triggers(),
            copy.player(),
            copy.bounds(),
            copy.getCollisionManager()
        );
    }

    @Override
    public Collection<Obstacle> obstacles() {
        return Collections.unmodifiableCollection(this.obstacles);
    }

    @Override
    public Collection<Trigger> triggers() {
        return Collections.unmodifiableCollection(this.triggers);
    }

    // unmodifiable
    @Override
    public PlayerModel player() {
        return this.player.get();
    }

    @Override
    public Dimension bounds() {
        return this.bounds;
    }

    @Override
    public boolean removeGameObjcts(final MutableObject collidableGameObject) {
        return this.triggers.remove(collidableGameObject);
    }

    @Override
    public CollisionManager getCollisionManager() {
        return this.collisionManager;
    }

    @Override
    public Collection<MutableObject> objects() {
        return Sets.union(this.obstacles, this.triggers);
    }

}
