package com.project.paradoxplatformer.model.world;

import com.project.paradoxplatformer.model.obstacles.api.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.model.world.api.WorldBuilder;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import java.util.List;
import java.util.ArrayList;

public class WordBuilderImpl implements WorldBuilder {

    private final List<Trigger> triggers;
    private final List<Obstacle> obstacles;
    private PlayerModel player;
    private Dimension bounds;
    private boolean isBuild;

    public WordBuilderImpl() {
        this.obstacles = new ArrayList<>();
        this.triggers = new ArrayList<>();
        this.isBuild = false;
    }

    @Override
    public WorldBuilder addPlayer(PlayerModel playerModel) {
        buildCheck();
        this.player = playerModel;
        return this;
    }

    @Override
    public WorldBuilder addTrigger(Trigger ...trigger) {
        buildCheck();
        this.triggers.addAll(List.of(trigger));
        return this;
    }

    @Override
    public WorldBuilder addObstacle(Obstacle ...obstacle) {
        buildCheck();
        this.obstacles.addAll(List.of(obstacle));
        return this;
    }

    @Override
    public WorldBuilder addbounds(Dimension dimension) {
        buildCheck();
        this.bounds = dimension;
        return this;
    }

    @Override
    public World build() {
        buildCheck();
        this.isBuild = true;
        return new WorldImpl(obstacles, triggers, player, bounds);
    }
    
    private void buildCheck() {
        if(this.isBuild) {
            throw new IllegalStateException("World is already build, cannot rebuild!");
        }
    }
}
