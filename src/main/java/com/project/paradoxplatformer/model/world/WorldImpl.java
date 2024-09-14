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

public final class WorldImpl implements World {

    private final Set<Obstacle> obstacles;
    private final Set<Trigger> triggers;
    private final SecureWrapper<PlayerModel> player;
    private final Dimension bounds;

    public WorldImpl(final Collection<Obstacle> obstacles, final Collection<Trigger> triggers, final PlayerModel player,
            final Dimension bounds) {
        this.obstacles = new LinkedHashSet<>(obstacles);
        this.triggers = new LinkedHashSet<>(triggers);
        this.player = SecureWrapper.of(player);
        this.bounds = bounds;
    }

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
    public boolean removeGameObjects(final MutableObject mutableGameObj) {
        if (mutableGameObj instanceof Trigger) {
            return this.triggers.remove(mutableGameObj);
        } else if (mutableGameObj instanceof Obstacle) {

            var y = this.obstacles.remove(mutableGameObj);
            return y;
        }
        return false;
    }

    @Override
    public Collection<MutableObject> gameObjects() {
        return Sets.union(Sets.union(this.obstacles, this.triggers), Set.of(this.player()));
    }

}
