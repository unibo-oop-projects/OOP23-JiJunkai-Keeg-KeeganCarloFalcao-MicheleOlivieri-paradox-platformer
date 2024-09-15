package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
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
public abstract class AbstractTransformableObject extends AbstractPositionableObject {

    protected Dimension dimension;
    protected Vector2D heightVector;
    protected Vector2D widthVector;

    /**
     * Constructs an {@code AbstractTransformableObject} with the specified
     * initial position and dimension.
     * 
     * @param position  the initial position of the object as a {@link Coord2D}
     *                  object
     * @param dimension the initial dimension of the object as a {@link Dimension}
     *                  object
     */
    protected AbstractTransformableObject(final Coord2D position, final Dimension dimension) {
        super(position);
        this.dimension = dimension;
        this.heightVector = new Simple2DVector(0.d, dimension.height());
        this.widthVector = new Simple2DVector(dimension.width(), 0.);
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
     * Sets the position of this object.
     * 
     * @param position the new position to set as a {@link Coord2D} object
     */
    @Override
    public void setPosition(Coord2D position) {
        this.position = position;
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
     * Updates the state of this object based on the time delta.
     * <p>
     * This method must be implemented by subclasses to define how the object's
     * state
     * changes over time.
     * </p>
     * 
     * @param dt the time delta to use for updating the state
     */
    @Override
    public abstract void updateState(long dt);

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
        return 0;
    }
}
