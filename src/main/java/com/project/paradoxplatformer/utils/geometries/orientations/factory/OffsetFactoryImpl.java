package com.project.paradoxplatformer.utils.geometries.orientations.factory;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.orientations.BoxOffset;
import com.project.paradoxplatformer.utils.geometries.orientations.Offset;
import java.util.function.BiFunction;

/**
 * Implementation of the {@link OffsetFactory} interface for creating various
 * types of offsets
 * based on the dimensions of an anchored object.
 * <p>
 * This class provides methods to create offsets relative to different corners
 * of a dimension,
 * as well as a method to create a {@link BoxOffset} based on a given dimension.
 * </p>
 */
public final class OffsetFactoryImpl implements OffsetFactory {

    private final Dimension anchored;

    /**
     * Constructs an {@link OffsetFactoryImpl} with the specified anchored
     * dimension.
     * <p>
     * This dimension will be used to calculate offsets relative to its corners.
     * </p>
     *
     * @param anchordDim the dimension to use as the anchor for offset calculations
     */
    public OffsetFactoryImpl(final Dimension anchordDim) {
        this.anchored = anchordDim;
    }

    @Override
    public Offset topLeft() {
        /**
         * Creates an offset for the top-left corner of the anchored dimension.
         * <p>
         * This offset maps to the coordinate (0, 0) relative to the anchored dimension.
         * </p>
         *
         * @return an {@link Offset} representing the top-left corner
         */
        return new TemplateOffset((x, y) -> new Coord2D(x, y));
    }

    @Override
    public Offset bottomRight() {
        /**
         * Creates an offset for the bottom-right corner of the anchored dimension.
         * <p>
         * This offset maps to the coordinate (width - x, height - y) relative to the
         * anchored dimension.
         * </p>
         *
         * @return an {@link Offset} representing the bottom-right corner
         */
        return new TemplateOffset((x, y) -> new Coord2D(anchored.width() - x, anchored.height() - y));
    }

    @Override
    public Offset bottomLeft() {
        /**
         * Creates an offset for the bottom-left corner of the anchored dimension.
         * <p>
         * This offset maps to the coordinate (x, height - y) relative to the anchored
         * dimension.
         * </p>
         *
         * @return an {@link Offset} representing the bottom-left corner
         */
        return new TemplateOffset((x, y) -> new Coord2D(x, anchored.height() - y));
    }

    @Override
    public Offset topRight() {
        /**
         * Creates an offset for the top-right corner of the anchored dimension.
         * <p>
         * This offset maps to the coordinate (width - x, y) relative to the anchored
         * dimension.
         * </p>
         *
         * @return an {@link Offset} representing the top-right corner
         */
        return new TemplateOffset((x, y) -> new Coord2D(anchored.width() - x, y));
    }

    @Override
    public BoxOffset<Dimension> boxOffset() {
        /**
         * Creates a {@link BoxOffset} that is relative to a given dimension.
         * <p>
         * This method creates an offset where the vertical coordinate is adjusted by
         * the height of the given dimension,
         * while the horizontal coordinate is set to 0.
         * </p>
         *
         * @return a {@link BoxOffset} instance that adjusts based on the provided
         *         dimension
         */
        return boxDim -> OffsetFactoryImpl.this.new TemplateOffset(
                (x, y) -> new Coord2D(0, y + boxDim.height())).anchor(new BaseOffset());
    }

    /**
     * Implementation of the {@link Offset} interface using a {@link BiFunction} to
     * map coordinates.
     * <p>
     * This inner class provides a way to calculate a coordinate based on a mapping
     * function and an anchor.
     * </p>
     */
    private final class TemplateOffset implements Offset {
        private Coord2D combined;
        private final BiFunction<Double, Double, Coord2D> poleMapper;

        /**
         * Constructs a {@link TemplateOffset} with the specified mapping function.
         * <p>
         * The mapping function is used to calculate the coordinate based on the given x
         * and y values.
         * </p>
         *
         * @param poleMapper a {@link BiFunction} that maps x and y values to a
         *                   {@link Coord2D}
         */
        TemplateOffset(final BiFunction<Double, Double, Coord2D> poleMapper) {
            this.poleMapper = poleMapper;
        }

        @Override
        public Offset anchor(final Offset offset) {
            /**
             * Anchors the offset to the given offset.
             * <p>
             * This method applies the anchor offset to the calculated coordinate using the
             * mapping function.
             * </p>
             *
             * @param offset the offset to anchor to
             * @return the updated {@link Offset} with the applied anchor
             */
            final var supplyCoord = offset.get();
            this.combined = poleMapper.apply(supplyCoord.x(), supplyCoord.y());
            return this;
        }

        @Override
        public Coord2D get() {
            /**
             * Gets the calculated coordinate.
             * <p>
             * This method returns the coordinate that was calculated based on the anchor
             * and the mapping function.
             * </p>
             *
             * @return the calculated {@link Coord2D} coordinate
             */
            return this.combined;
        }
    }
}
