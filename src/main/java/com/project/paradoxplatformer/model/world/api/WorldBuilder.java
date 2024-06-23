package com.project.paradoxplatformer.model.world.api;

import com.project.paradoxplatformer.model.obstacles.api.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.utils.geometries.Dimension;


public interface WorldBuilder {
    
    WorldBuilder addPlayer(PlayerModel playerModel);

    WorldBuilder addTrigger(Trigger ...trigger);

    WorldBuilder addObstacle(Obstacle ...obstacle);

    WorldBuilder addbounds(Dimension dimension);

    World build();
}
