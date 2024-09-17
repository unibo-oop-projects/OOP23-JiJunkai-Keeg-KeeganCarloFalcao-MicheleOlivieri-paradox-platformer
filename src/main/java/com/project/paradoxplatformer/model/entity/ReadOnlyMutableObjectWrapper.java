package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * A read-only wrapper for a {@link MutableObject}.
 * <p>
 * This class decorates a {@link MutableObject} to prevent modification of its
 * state.
 * It allows access to the wrapped object's state but disallows any changes to
 * it.
 * </p>
 */
public class ReadOnlyMutableObjectWrapper implements MutableObject {

    private final MutableObject wrapped;

    /**
     * Constructs a {@code ReadOnlyMutableObjectWrapper} with the specified
     * {@link MutableObject}.
     *
     * @param abstractDecorator the {@link MutableObject} to wrap
     */
    public ReadOnlyMutableObjectWrapper(final MutableObject abstractDecorator) {
        this.wrapped = abstractDecorator;
    }

    /**
     * Returns the position of the wrapped {@link MutableObject}.
     *
     * @return the position as a {@link Coord2D}
     */
    @Override
    public Coord2D getPosition() {
        return this.wrapped.getPosition();
    }

    /**
     * Returns the dimension of the wrapped {@link MutableObject}.
     *
     * @return the dimension as a {@link Dimension}
     */
    @Override
    public Dimension getDimension() {
        return this.wrapped.getDimension();
    }

    /**
     * Returns the ID of the wrapped {@link MutableObject}.
     *
     * @return the ID as an {@code int}
     */
    @Override
    public int getID() {
        return this.wrapped.getID();
    }

    /**
     * Returns the collision type of the wrapped {@link MutableObject}.
     *
     * @return the collision type as a {@link CollisionType}
     */
    @Override
    public CollisionType getCollisionType() {
        return this.wrapped.getCollisionType();
    }

    /**
     * Throws {@link UnsupportedOperationException} because setting the position is
     * not allowed.
     *
     * @param position the new position (ignored)
     * @throws UnsupportedOperationException if called
     */
    @Override
    public void setPosition(final Coord2D position) {
        throw new UnsupportedOperationException("Unable to execute 'setPosition'");
    }

    /**
     * Throws {@link UnsupportedOperationException} because setting the dimension is
     * not allowed.
     *
     * @param dimension the new dimension (ignored)
     * @throws UnsupportedOperationException if called
     */
    @Override
    public void setDimension(final Dimension dimension) {
        throw new UnsupportedOperationException("Unable to execute 'setDimension'");
    }

    /**
     * Throws {@link UnsupportedOperationException} because updating the state is
     * not allowed.
     *
     * @param dt the time delta (ignored)
     * @throws UnsupportedOperationException if called
     */
    @Override
    public void updateState(final long dt) {
        throw new UnsupportedOperationException("Unable to execute 'updateState'");
    }

    /**
     * Returns the speed of the wrapped {@link MutableObject}.
     *
     * @return the speed as a {@link Vector2D}
     */
    public Vector2D getSpeed() {
        return this.wrapped.getSpeed();
    }

    /**
     * Returns the base delta of the wrapped {@link MutableObject}.
     *
     * @return the base delta as a {@code double}
     */
    @Override
    public double getBaseDelta() {
        return this.wrapped.getBaseDelta();
    }
}
