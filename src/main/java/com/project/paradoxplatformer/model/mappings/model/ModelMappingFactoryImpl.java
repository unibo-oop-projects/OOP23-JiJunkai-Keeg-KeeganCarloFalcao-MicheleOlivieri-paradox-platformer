package com.project.paradoxplatformer.model.mappings.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.TrajMacro;
import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.entity.TrasformType;
import com.project.paradoxplatformer.model.mappings.EntityDataMapper;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.Trigger;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;

/**
 * Defines a basic Mapping factory for mapping a game data object to a model game object.
 */
public final class ModelMappingFactoryImpl implements ModelMappingFactory {

    private static final String DOT = ".";
    private static final String OBSTACLE_PREFIX_NAME = Obstacle.class.getPackageName() + DOT;
    private static final String TRIGGER_PREFIX_NAME = Trigger.class.getPackageName() + DOT;
    private static final String OBSTACLE_TAG = "obstacle";
    private static final String TRIGGER_TAG = "trigger";

    /**
     * Non argument model constructor for the factory pattern although it could habe static methods.
     */
    public ModelMappingFactoryImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityDataMapper<PlayerModel> playerToModel() {
        return g -> new PlayerModel(g.getID(), new Coord2D(g.getX(), g.getY()), new Dimension(g.getWidth(), g.getHeight()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityDataMapper<Obstacle> obstacleToModel() {
        return this::evaluateObstacleType;
    }

    private Obstacle evaluateObstacleType(final GameDTO sub) {
        return (Obstacle) evaluateGenericType(sub, OBSTACLE_PREFIX_NAME, OBSTACLE_TAG);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityDataMapper<? extends Trigger> triggerToModel() {
        return this::evaluateTriggerType;
    }

    //Gets the trigger and it associates a trigger to an object only if it is eligible 
    //(for integer null is ofter represented as -1)
    private Trigger evaluateTriggerType(final GameDTO sub) {
        final var trigger = (Trigger) evaluateGenericType(sub, TRIGGER_PREFIX_NAME, TRIGGER_TAG);
        trigger.setTriggerableID(Optional.of(sub.getTriggeringId()).filter(i -> i > 0));
        return trigger;
    }

    /**
     * Retrives the game object through reflection from the json data object.
     * @param sub the game data objects (json)
     * @param prefix the prefix to find where the tree package of that game object is located 
     * in order to get the class full name
     * @param typeTag to determin wether it had to create an obstacle or a trigger
     * @return the game object instance (is has to be cast)
     */
    private Object evaluateGenericType(final GameDTO sub, final String prefix, final String typeTag) {
        try {
            return Class.forName(prefix + sub.getSubtype())
                    .getConstructor(
                            int.class,
                            Coord2D.class,
                            Dimension.class,
                            Queue.class
                    )
                    .newInstance(
                            sub.getID(),
                            new Coord2D(sub.getX(), sub.getY()),
                            new Dimension(sub.getWidth(), sub.getHeight()),
                            this.trajMacro(sub.getTraj())
                    );
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            throw new IllegalStateException("failed to create " + typeTag + " through reflection\nCheck: ", e);
        }
    }

    /**
     * Retrives the trajectory definitions (macros) and trasforms it to a queue.
     * @param traj the trajectory macros
     * @return a queue with all trajectory infos stored
     */
    private Queue<TrajectoryInfo> trajMacro(final TrajMacro[] traj) {
        if (Objects.nonNull(traj)) {
            return Arrays.stream(traj)
                    .map(t -> new TrajectoryInfo(new Simple2DVector(t.getX(), t.getY()),
                            t.getDuration(),
                            TrasformType.valueOf(t.getVector())))
                    .collect(Collectors.toCollection(LinkedList::new));
        }
        return new LinkedList<>();
    }

}
