package com.project.paradoxplatformer.model.mappings.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.TrajMacro;
import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.entity.TrasformType;
import com.project.paradoxplatformer.model.mappings.EntityDataMapper;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;

public class ModelMappingFactoryImpl implements ModelMappingFactory {

    private static final String DOT = ".";
    private static final String OBSTACLE_PREFIX_NAME = Obstacle.class.getPackageName() + DOT;
    private static final String TRIGGER_PREFIX_NAME = Trigger.class.getPackageName() + DOT;
    private static final String OBSTACLE_TAG = "obstacle";
    private static final String TRIGGER_TAG = "trigger";

    public ModelMappingFactoryImpl() {
    }

    @Override
    public EntityDataMapper<PlayerModel> playerToModel() {
        return g -> new PlayerModel(new Coord2D(g.getX(), g.getY()), new Dimension(g.getWidth(), g.getHeight()));
    }

    @Override
    public EntityDataMapper<Obstacle> obstacleToModel() {
        return this::evaluateObstacleType;
    }

    private Obstacle evaluateObstacleType(GameDTO sub) {
        return (Obstacle) evaluateGenericType(sub, OBSTACLE_PREFIX_NAME, OBSTACLE_TAG);
    }


    private Queue<TrajectoryInfo> trajMacro(TrajMacro[] traj) {
        if (Objects.nonNull(traj)) {
            return Arrays.stream(traj)
                    .map(t -> new TrajectoryInfo(new Simple2DVector(t.getX(), t.getY()),
                            t.getDuration(),
                            TrasformType.valueOf(t.getVector())))
                    .collect(Collectors.toCollection(LinkedList::new));
        }
        return new LinkedList<>();
    }

    @Override
    public EntityDataMapper<? extends Trigger> triggerToModel() {
        return this::evaluateTriggerType;
    }

    private Trigger evaluateTriggerType(GameDTO sub) {
        return (Trigger) evaluateGenericType(sub, TRIGGER_PREFIX_NAME, TRIGGER_TAG);
    }


    private Object evaluateGenericType(GameDTO sub, String prefix, final String typeTag) {
        try {
            return Class.forName(prefix + sub.getSubtype())
                    .getConstructor(
                        Coord2D.class,
                        Dimension.class
                    ).newInstance(
                        new Coord2D(sub.getX(), sub.getY()),
                        new Dimension(sub.getWidth(), sub.getHeight())
                    );
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            throw new IllegalStateException("failed to create " + typeTag + " through reflection\nCheck: ", e);
        }
    }

}
