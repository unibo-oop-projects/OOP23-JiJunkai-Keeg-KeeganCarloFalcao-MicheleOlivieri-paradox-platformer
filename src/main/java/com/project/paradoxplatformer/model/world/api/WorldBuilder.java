package com.project.paradoxplatformer.model.world.api;

import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.utils.geometries.Dimension;

public interface WorldBuilder {

    WorldBuilder addPlayer(final PlayerModel playerModel);

    WorldBuilder addTrigger(final Trigger... trigger);

    WorldBuilder addObstacle(final Obstacle... obstacle);

    WorldBuilder addbounds(final Dimension dimension);

    World build();
}
