package com.project.paradoxplatformer.model.world;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.api.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import com.project.paradoxplatformer.utils.geometries.vector.Polar2DVector;
import java.util.Queue;


//possibly make it separate or build some hierarchy for efficiency
//make it for view too
public class DataMapper {


    //better make a param for delta and limit speed
    public static Function<GameDTO, PlayerModel> playerToModel() {
        return g -> new PlayerModel(new Coord2D(g.getX(), g.getY()), Polar2DVector.nullVector());
    }

    public static Function<GameDTO, Obstacle> obstacleToModel() {
        return DataMapper::evaluateObstacleType;
    }

    private static Obstacle evaluateObstacleType(GameDTO sub) {
        try {
            return (Obstacle) Class.forName(sub.getSubtype())
                .getConstructor(
                    Coord2D.class, 
                    Dimension.class,
                    Queue.class,
                    PlayerModel.class
                    )
                .newInstance(
                    new Coord2D(sub.getX(), sub.getY()),
                    new Dimension(sub.getWidth(), sub.getHeight()),
                    null,
                    null
                );
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
