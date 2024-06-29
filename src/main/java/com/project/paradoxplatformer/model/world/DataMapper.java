package com.project.paradoxplatformer.model.world;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.TrajMacro;
import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.entity.TrasformType;
import com.project.paradoxplatformer.model.obstacles.api.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import com.project.paradoxplatformer.utils.geometries.vector.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.Simple2DVector;

import java.util.Queue;
import java.util.Objects;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.LinkedList;
import java.util.Collections;
import java.util.List;

//possibly make it separate or build some hierarchy for efficiency
//make it for view too
public class DataMapper {

    private static final String OBSTACLE_PREFIX_NAME = "com.project.paradoxplatformer.model.obstacles.";

    //better make a param for delta and limit speed
    public static Function<GameDTO, PlayerModel> playerToModel() {
        return g -> new PlayerModel(new Coord2D(g.getX(), g.getY()), new Dimension(g.getWidth(), g.getHeight()), Polar2DVector.nullVector());
    }

    public static Function<GameDTO, Obstacle> obstacleToModel() {
        return DataMapper::evaluateObstacleType;
    }

    private static Obstacle evaluateObstacleType(GameDTO sub) {
        try {
            return (Obstacle) Class.forName(OBSTACLE_PREFIX_NAME + sub.getSubtype())
                .getConstructor(
                    Coord2D.class, 
                    Dimension.class,
                    Queue.class
                    )
                .newInstance(
                    new Coord2D(sub.getX(), sub.getY()),
                    new Dimension(sub.getWidth(), sub.getHeight()),
                    DataMapper.trajMacro(sub.getTraj())
                );
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Queue<TrajectoryInfo> trajMacro(TrajMacro[] traj) {
        if(Objects.nonNull(traj)) {
            return Arrays.stream(traj)
                .map(t -> 
                    new TrajectoryInfo(new Simple2DVector(t.getX(), t.getY()),
                        t.getDuration(),
                        TrasformType.valueOf(t.getVector())
                    )
                )
                .collect(Collectors.toCollection(LinkedList::new));
        }
        return new LinkedList<>();
    }
}
