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
import com.project.paradoxplatformer.utils.geometries.vector.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.Simple2DVector;

public class ModelMappingFactoryImpl implements ModelMappingFactory {

    private static final String DOT = ".";
    private static final String OBSTACLE_PREFIX_NAME = Obstacle.class.getPackageName() + DOT;

    public ModelMappingFactoryImpl() {
    }

    @Override
    public EntityDataMapper<PlayerModel> playerToModel() {
        return g -> new PlayerModel(new Coord2D(g.getX(), g.getY()), new Dimension(g.getWidth(), g.getHeight()),
                Polar2DVector.nullVector());
    }

    @Override
    public EntityDataMapper<Obstacle> obstacleToModel() {
        return this::evaluateObstacleType;
    }

    private Obstacle evaluateObstacleType(GameDTO sub) {
        try {
            return (Obstacle) Class.forName(OBSTACLE_PREFIX_NAME + sub.getSubtype())
                    .getConstructor(
                            Coord2D.class,
                            Dimension.class,
                            Queue.class)
                    .newInstance(
                            new Coord2D(sub.getX(), sub.getY()),
                            new Dimension(sub.getWidth(), sub.getHeight()),
                            this.trajMacro(sub.getTraj()));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            throw new IllegalStateException("failed to create obstacle through reflection\nCheck: ", e);
        }

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'triggerToModel'");
    }

}
