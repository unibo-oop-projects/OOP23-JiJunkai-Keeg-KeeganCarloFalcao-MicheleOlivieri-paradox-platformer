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
    position and dimension adjustments.
 * <p>
 * This class extends {@link AbstractPositionableObject} to add functionality
 * for managing dimensions and transformation vectors. It provides methods to
 * get and set the object's dimensions, as well as default implementations for
 * speed and base delta.
 * </p>
 */
public abstract class AbstractTransformableObject extends AbstractPositionableObject {
    private static final double BASE_DELTA = 0.0;
    private Dimension dimension;
    private Vector2D heightVector;
    private Vector2D widthVector;
    private final Queue<TrajectoryInfo> transformationStats;
    private final double anchorY;
    private final double anchorHeight;
    private boolean isIdle;
    private final PhysicsEngine mover;
    private final InterpolatorFactory interpolatorFactory;

    /**
     * Constructs an {@code AbstractTransformableObject} with the specified
     * initial position, dimension, and trajectory queue.
     * 
     * @param key             the unique id of the positional game object
     * @param position        the initial position of the object as a
     *                        {@link Coord2D} object
     * @param dimension       the initial dimension of the object as a
     *                        {@link Dimension} object
     * @param trajectoryQueue the initial queue of trajectory transformations
     */
    protected AbstractTransformableObject(
            final int key,
            final Coord2D position,
            final Dimension dimension,
            final Queue<TrajectoryInfo> trajectoryQueue) {
        super(key, position);
        this.dimension = dimension;
        this.mover = new PhysicsEngine();
        this.interpolatorFactory = new InterpolatorFactoryImpl();
        this.heightVector = new Simple2DVector(0.0, dimension.height());
        this.widthVector = new Simple2DVector(dimension.width(), 0.0);
        this.transformationStats = trajectoryQueue;
        this.isIdle = true;
        this.anchorY = position.y();
        this.anchorHeight = dimension.height();
    }

    /**
     * Constructs an {@code AbstractTransformableObject} with the specified
     * initial position and dimension, using an empty trajectory queue.
     * 
     * @param key       the unique id of the positional game object
     * @param position  the initial position of the object as a {@link Coord2D}
     *                  object
     * @param dimension the initial dimension of the object as a {@link Dimension}
     *                  object
     */
    protected AbstractTransformableObject(final int key, final Coord2D position, final Dimension dimension) {
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
     * This method returns a null vector, indicating that the object has no speed
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
     * This method updates the object's dimension and position vectors based on
     * the current trajectory transformation. It uses the move engine and
     * interpolator factory to perform the transformations.
     * </p>
     * 
     * @param dt the time delta to use for updating the state
     */
    @Override
    public void updateState(final long dt) {
        if (!this.isIdle && !this.transformationStats.isEmpty()) {
            final TrajectoryInfo currentTransf = transformationStats.peek();
            switch (currentTransf.transfType()) {
                case DISPLACEMENT:
                    setDisplacement(this.transform(getDisplacement(), currentTransf, dt).getKey());
                    break;
                case HEIGHT:
                    setDisplacement(this.mover.moveTo(
                            getDisplacement(),
                            currentTransf.endpoint().sub(new Simple2DVector(0.0, (anchorHeight - anchorY))),
                            currentTransf.duration(),
                            interpolatorFactory.easeIn(),
                            dt).getKey());
                    this.heightVector = this.transform(this.heightVector, currentTransf, dt).getKey();
                    break;
                case WIDTH:
                    this.widthVector = this.transform(this.widthVector, currentTransf, dt).getKey();
                    break;
                default:
                    throw new IllegalStateException("Unexpected transformation type: " + currentTransf.transfType());
            }
        }
    }

    // Removes the current transformation when finished
    private void popWhenFinished(final double percentage) {
        if (percentage >= 1.0) {
            this.mover.stop();
            this.transformationStats.remove();
        }
    }

    private Pair<Vector2D, Double> transform(final Vector2D vector2d, final TrajectoryInfo trajectoryInfo,
            final long dt) {
        final Pair<Vector2D, Double> transf = this.mover.moveTo(
                vector2d,
                trajectoryInfo.endpoint(),
                trajectoryInfo.duration(),
                interpolatorFactory.easeIn(),
                dt);
        this.popWhenFinished(transf.getValue());
        return transf;
    }

    /**
     * Returns the height vector of this object.
     * 
     * @return the height vector as a {@link Vector2D} object
     */
    protected Vector2D getHeightVector() {
        return heightVector;
    }

    /**
     * Sets the height vector of this object.
     * 
     * @param heightVector the new height vector to set as a {@link Vector2D} object
     */
    protected void setHeightVector(final Vector2D heightVector) {
        this.heightVector = heightVector;
    }

    /**
     * Returns the width vector of this object.
     * 
     * @return the width vector as a {@link Vector2D} object
     */
    protected Vector2D getWidthVector() {
        return widthVector;
    }

    /**
     * Sets the width vector of this object.
     * 
     * @param widthVector the new width vector to set as a {@link Vector2D} object
     */
    protected void setWidthVector(final Vector2D widthVector) {
        this.widthVector = widthVector;
    }

    /**
     * Checks if this object is idle.
     * 
     * @return true if the object is idle, false otherwise
     */
    protected boolean isIdle() {
        return isIdle;
    }

    /**
     * Sets the idle state of this object.
     * 
     * @param isIdle true to set the object as idle, false otherwise
     */
    protected void setIdle(final boolean isIdle) {
        this.isIdle = isIdle;
    }
}
