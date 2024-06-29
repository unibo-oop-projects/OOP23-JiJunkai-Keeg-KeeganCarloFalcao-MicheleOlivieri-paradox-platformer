package com.project.paradoxplatformer.model.world;

import java.util.*;

import com.project.paradoxplatformer.model.obstacles.api.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.utils.geometries.Dimension;

public class WorldImpl implements World{

    private final List<Obstacle> obstacles;
    private final List<Trigger> triggers;
    private final PlayerModel player;
    private final Dimension bounds;

    public WorldImpl(final List<Obstacle> obstacles, final List<Trigger> triggers, final PlayerModel player, final Dimension bounds) {
        this.obstacles = obstacles;
        this.triggers = triggers;
        this.player = player;
        this.bounds = bounds;
    }

    @Override
    public Collection<Obstacle> obstacles() {
        return Collections.unmodifiableCollection(this.obstacles);
    }

    @Override
    public Collection<Trigger> triggers() {
        return Collections.unmodifiableCollection(this.triggers);
    }

    //unmodifiable
    @Override
    public PlayerModel player() {
        return this.player;
    }

    //Should be unmodifiable
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

    
    
}
