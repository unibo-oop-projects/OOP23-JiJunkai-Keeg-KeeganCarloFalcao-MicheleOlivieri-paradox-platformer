package com.project.paradoxplatformer.model.world;

import java.util.*;

import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.utils.SecureWrapper;
import com.project.paradoxplatformer.utils.collision.CollisionManager;
import com.project.paradoxplatformer.utils.geometries.Dimension;

public final class WorldImpl implements World {

    private final List<Obstacle> obstacles;
    private final List<Trigger> triggers;
    private final SecureWrapper<PlayerModel> player;
    private final Dimension bounds;
    private final CollisionManager collisionManager;

    public WorldImpl(final List<Obstacle> obstacles, final List<Trigger> triggers, final PlayerModel player,
            final Dimension bounds, CollisionManager collisionManager) {
        this.obstacles = new ArrayList<>(obstacles);
        this.triggers = new ArrayList<>(triggers);
        this.player = SecureWrapper.of(player);
        this.bounds = bounds;
        this.collisionManager = collisionManager;
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

    // Should be unmodifiable
    @Override
    public Dimension bounds() {
        return this.bounds;
    }

    @Override
    public boolean removeTrigger(Trigger selectTrigger) {
        return this.triggers.remove(selectTrigger);
    }

    @Override
    public boolean removeObstacle(Obstacle selectObstacle) {
        return this.obstacles.remove(selectObstacle);
    }

    @Override
    public CollisionManager getCollisionManager() {
        return this.collisionManager;
    }

}
