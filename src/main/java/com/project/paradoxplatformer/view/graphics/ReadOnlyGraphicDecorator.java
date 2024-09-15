package com.project.paradoxplatformer.view.graphics;

import java.util.Objects;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import javafx.beans.value.ObservableDoubleValue;

/**
 * A read-only decorator for {@link GraphicAdapter}. This class wraps an
 * existing
 * {@link GraphicAdapter} and exposes its properties and methods while
 * preventing any modifications.
 * 
 * <p>
 * Note: This class is not designed for extension. If you need to extend its
 * functionality,
 * consider modifying the existing methods or creating a new decorator class.
 * </p>
 *
 * @param <C> The type of the graphical component.
 */
public final class ReadOnlyGraphicDecorator<C> implements GraphicAdapter<C> {

    private final GraphicAdapter<C> graphicReader;

    /**
     * Constructs a ReadOnlyGraphicDecorator with the specified graphic adapter.
     *
     * @param copyGraphic The graphic adapter to be decorated. Must not be null.
     * @throws NullPointerException If {@code copyGraphic} is null.
     */
    public ReadOnlyGraphicDecorator(final GraphicAdapter<C> copyGraphic) {
        Objects.requireNonNull(copyGraphic, "Graphic adapter cannot be null");
        this.graphicReader = copyGraphic;
    }

    /**
     * Returns the underlying graphic adapter.
     *
     * <p>
     * Note: This method is exposed as part of the decorator pattern. Subclasses or
     * users
     * should be aware that it provides access to the wrapped adapter's
     * functionality.
     * </p>
     *
     * @return The underlying graphic adapter.
     */
    @Override
    public C unwrap() {
        return this.graphicReader.unwrap();
    }

    /**
     * Returns the dimension of the graphical component.
     *
     * <p>
     * Note: This method is exposed as part of the decorator pattern. Subclasses or
     * users
     * should be aware that it provides the dimension from the wrapped adapter.
     * </p>
     *
     * @return The dimension of the graphical component.
     */
    @Override
    public Dimension dimension() {
        return this.graphicReader.dimension();
    }

    /**
     * Returns the absolute position of the graphical component.
     *
     * <p>
     * Note: This method is exposed as part of the decorator pattern. Subclasses or
     * users
     * should be aware that it provides the absolute position from the wrapped
     * adapter.
     * </p>
     *
     * @return The absolute position of the graphical component.
     */
    @Override
    public Coord2D absolutePosition() {
        return this.graphicReader.absolutePosition();
    }

    /**
     * Returns the relative position of the graphical component.
     *
     * <p>
     * Note: This method is exposed as part of the decorator pattern. Subclasses or
     * users
     * should be aware that it provides the relative position from the wrapped
     * adapter.
     * </p>
     *
     * @return The relative position of the graphical component.
     */
    @Override
    public Coord2D relativePosition() {
        return this.graphicReader.relativePosition();
    }

    /**
     * Throws an {@link UnsupportedOperationException} because setting the dimension
     * is not supported.
     *
     * @param width  The width to set.
     * @param height The height to set.
     * @throws UnsupportedOperationException Always thrown.
     */
    @Override
    public void setDimension(final double width, final double height) {
        throw new UnsupportedOperationException("Unable to execute method 'setDimension'");
    }

    /**
     * Throws an {@link UnsupportedOperationException} because setting the position
     * is not supported.
     *
     * @param x The x-coordinate to set.
     * @param y The y-coordinate to set.
     * @throws UnsupportedOperationException Always thrown.
     */
    @Override
    public void setPosition(final double x, final double y) {
        throw new UnsupportedOperationException("Unable to execute 'setPosition'");
    }

    /**
     * Throws an {@link UnsupportedOperationException} because translating the
     * position is not supported.
     *
     * @param x The x-coordinate to translate.
     * @param y The y-coordinate to translate.
     * @throws UnsupportedOperationException Always thrown.
     */
    @Override
    public void translate(final double x, final double y) {
        throw new UnsupportedOperationException("Unable to execute 'translate'");
    }

    /**
     * Throws an {@link UnsupportedOperationException} because binding properties is
     * not supported.
     *
     * @param wRatio The observable ratio for the width.
     * @param hRatio The observable ratio for the height.
     * @throws UnsupportedOperationException Always thrown.
     */
    @Override
    public void bindProperties(final ObservableDoubleValue wRatio, final ObservableDoubleValue hRatio) {
        throw new UnsupportedOperationException("Unable to execute 'bindProperties'");
    }

    /**
     * Throws an {@link UnsupportedOperationException} because flipping the graphic
     * is not supported.
     *
     * @throws UnsupportedOperationException Always thrown.
     */
    @Override
    public void flip() {
        throw new UnsupportedOperationException("Unable to execute method 'flip'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((graphicReader == null) ? 0 : graphicReader.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ReadOnlyGraphicDecorator<?> that = (ReadOnlyGraphicDecorator<?>) obj;
        return Objects.equals(graphicReader, that.graphicReader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setKey(final int key) {
        this.graphicReader.setKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getID() {
        return graphicReader.getID();
    }
}
