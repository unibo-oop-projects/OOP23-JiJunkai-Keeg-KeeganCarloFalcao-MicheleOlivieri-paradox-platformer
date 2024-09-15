package com.project.paradoxplatformer.model.mappings.model;

import com.project.paradoxplatformer.model.mappings.EntityDataMapper;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.Trigger;

/**
 * A factory interface for creating {@link EntityDataMapper} instances
 * for different types of models used in the application.
 */
public interface ModelMappingFactory {

    /**
     * Creates an {@link EntityDataMapper} for mapping {@link PlayerModel} objects.
     *
     * @return an {@link EntityDataMapper} that maps data to {@link PlayerModel}
     */
    EntityDataMapper<PlayerModel> playerToModel();

    /**
     * Creates an {@link EntityDataMapper} for mapping {@link Obstacle} objects.
     * The mapper can handle instances of {@link Obstacle} and its subclasses.
     *
     * @return an {@link EntityDataMapper} that maps data to {@link Obstacle} and
     *         its subclasses
     */
    EntityDataMapper<? extends Obstacle> obstacleToModel();

    /**
     * Creates an {@link EntityDataMapper} for mapping {@link Trigger} objects.
     * The mapper can handle instances of {@link Trigger} and its subclasses.
     *
     * @return an {@link EntityDataMapper} that maps data to {@link Trigger} and its
     *         subclasses
     */
    EntityDataMapper<? extends Trigger> triggerToModel();
}
