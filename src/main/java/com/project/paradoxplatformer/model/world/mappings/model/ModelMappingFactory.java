package com.project.paradoxplatformer.model.world.mappings.model;

import com.project.paradoxplatformer.model.obstacles.api.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.world.mappings.EntityDataMapper;

public interface ModelMappingFactory {
    
    EntityDataMapper<PlayerModel> playerToModel();

    EntityDataMapper<Obstacle> obstacleToModel();
}
