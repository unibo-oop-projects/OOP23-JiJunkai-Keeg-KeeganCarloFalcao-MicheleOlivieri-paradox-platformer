package com.project.paradoxplatformer.model.mappings.model;

import com.project.paradoxplatformer.model.mappings.EntityDataMapper;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.Trigger;

public interface ModelMappingFactory {

    EntityDataMapper<PlayerModel> playerToModel();

    EntityDataMapper<? extends Obstacle> obstacleToModel();

    EntityDataMapper<? extends Trigger> triggerToModel();
}
