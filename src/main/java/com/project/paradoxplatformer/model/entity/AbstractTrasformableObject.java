package com.project.paradoxplatformer.model.entity;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactory;
import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.physic.PhysicsEngine;
import com.project.paradoxplatformer.utils.geometries.vector.api.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * An abstract class representing an object that can be transformed, including
 * position and dimension adjustments.
 * <p>
 * This class extends {@link AbstractPositionableObject} to add functionality
 * for managing dimensions and transformation vectors. It provides methods to
 * get and set the object's dimensions, as well as default implementations for
 * speed and base delta.
 * </p>
 */
public abstract class AbstractTrasformableObject extends AbstractPositionableObject {

    private static final double BASE_DELTA = 0.d;
    protected Dimension dimension;
    protected Vector2D heightVector;
    protected Vector2D widthVector;
    private final Queue<TrajectoryInfo> trasformationStats;
    private final double anchorY;
    private final double anchorHeight;
    protected boolean isIdle;
    private final PhysicsEngine mover;
    private final InterpolatorFactory interFactory;

    /**
     * Constructs an {@code AbstractTransformableObject} with the specified
     * initial position and dimension.
     * 
     * @param key       the unique id of the positional game object
     * @param position  the initial position of the object as a {@link Coord2D}
     *                  object
     * @param position  the initial position of the object as a {@link Coord2D}
     *                  object
     * @param dimension the initial dimension of the object as a {@link Dimension}
     *                  object
     */
    protected AbstractTrasformableObject(
            final int key,
            final Coord2D position,
            final Dimension dimension,
            final Queue<TrajectoryInfo> trajectoryQueue) {
        super(key, position);
        this.dimension = dimension;
        this.mover = new PhysicsEngine();
        this.interFactory = new InterpolatorFactoryImpl();
        this.heightVector = new Simple2DVector(0.d, dimension.height());
        this.widthVector = new Simple2DVector(dimension.width(), 0.);
        this.trasformationStats = trajectoryQueue;
        this.isIdle = true;
        this.anchorY = position.y();
        this.anchorHeight = dimension.height();
    }

    protected AbstractTrasformableObject(final int key, final Coord2D position, final Dimension dimension) {
        this(key, position, dimension, new LinkedList<>());
    }

    /**
     * Returns the current dimension of this object.
     * 
     * @return the dimension as a {@link Dimension} object
     */
    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

    /**
     * Sets the dimension of this object.
     * 
     * @param dimension the new dimension to set as a {@link Dimension} object
     */
    @Override
    public void setDimension(final Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * Returns the collision type of this object.
     * <p>
     * This method must be implemented by subclasses to provide the object's
     * collision type.
     * </p>
     * 
     * @return the collision type as a {@link CollisionType} object
     */
    @Override
    public abstract CollisionType getCollisionType();

    /**
     * Returns the speed of this object.
     * <p>
     * This method returns a null vector, which means that the object has no speed
     * by default. Subclasses may override this method to provide specific speed
     * implementations.
     * </p>
     * 
     * @return the speed as a {@link Vector2D} object
     */
    @Override
    public Vector2D getSpeed() {
        return Polar2DVector.nullVector();
    }

    /**
     * Returns the base delta value used for transformations.
     * <p>
     * This method returns 0 by default. Subclasses may override this method to
     * provide specific base delta values.
     * </p>
     * 
     * @return the base delta value
     */
    @Override
    public double getBaseDelta() {
        return BASE_DELTA;
    }

    /**
     * Updates the state of this object based on the time delta.
     * <p>
     * Upon trajectory trasformations it update its dimension and position vectors
     * based on the object move-sets
     * define in the json config file. Such implementation is a default effect which
     * all game objects (apart player) have
     * </p>
     * 
     * @param dt the time delta to use for updating the state
     */
    @Override
    public void updateState(final long dt) {
        if (!this.isIdle && !this.trasformationStats.isEmpty()) {
            final TrajectoryInfo currentTransf = trasformationStats.peek();
            switch (currentTransf.transfType()) {
                case DISPLACEMENT:
                    this.displacement = this.trasform(this.displacement, currentTransf, dt).getKey();
                    break;
                case HEIGHT:
                    this.displacement = this.mover.moveTo(
                            this.displacement,
                            currentTransf.endpoint().sub(new Simple2DVector(0.d, (anchorHeight - anchorY))),
                            currentTransf.duration(),
                            interFactory.easeIn(),
                            dt).getKey();
                    this.heightVector = this.trasform(this.heightVector, currentTransf, dt).getKey();
                    break;
                case WIDTH:
                    this.widthVector = this.trasform(this.widthVector, currentTransf, dt).getKey();
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    // When the current trasgormation movement definition is finished is removed
    // from queue
    private void popWhenFinished(final double percentage) {
        if (percentage >= 1.d) {
            this.mover.stop();
            this.trasformationStats.remove();
        }
    }

    private Pair<Vector2D, Double> trasform(final Vector2D vector2d, final TrajectoryInfo trajectoryInfo,
            final long dt) {
        final Pair<Vector2D, Double> transf = this.mover.moveTo(
                vector2d,
                trajectoryInfo.endpoint(),
                trajectoryInfo.duration(),
                interFactory.easeIn(),
                dt);
        this.popWhenFinished(transf.getValue());
        return transf;
    }

}
