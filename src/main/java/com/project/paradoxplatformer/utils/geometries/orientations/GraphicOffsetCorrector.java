package com.project.paradoxplatformer.utils.geometries.orientations;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * Corrects the graphical offset of a component based on the given layout, box
 * offset, and vector.
 * <p>
 * This class applies a correction to the position of a graphical component
 * using a specified layout,
 * a box offset, and a directional vector (versor). The correction is applied to
 * adjust the component's
 * position based on these parameters.
 * </p>
 */
public final class GraphicOffsetCorrector implements OffsetCorrector {

    private final Vector2D versor;
    private final BoxOffset<Dimension> box;
    private final Offset layout;

    /**
     * Constructs a {@link GraphicOffsetCorrector} with the specified layout, box
     * offset, and vector.
     * <p>
     * The constructor initializes the corrector with the layout used for anchoring,
     * the box offset to evaluate dimensions, and the vector to apply directional
     * adjustments.
     * </p>
     *
     * @param layout the {@link Offset} representing the layout for anchoring
     * @param box    the {@link BoxOffset} used to evaluate dimensions
     * @param versor the {@link Vector2D} representing the directional adjustment
     *               vector
     */
    public GraphicOffsetCorrector(final Offset layout, final BoxOffset<Dimension> box, final Vector2D versor) {
        this.layout = layout;
        this.box = box;
        this.versor = versor;
    }

    /**
     * Corrects the graphical offset of a component based on its dimension and
     * position.
     * <p>
     * This method adjusts the position of the graphical component by applying a
     * correction based on
     * the layout and box offset. It scales the position by the given vector and
     * adds the layout's origin.
     * </p>
     *
     * @param gComponent the {@link Dimension} of the graphical component to be
     *                   corrected
     * @param pos        the {@link Coord2D} representing the initial position of
     *                   the component
     * @return a {@link Coord2D} representing the corrected position of the
     *         component
     */
    @Override
    public Coord2D correct(final Dimension gComponent, final Coord2D pos) {
        // Calculate the layout origin based on the box offset and component dimension
        var layoutOrigin = this.layout.anchor(this.box.evaluate(gComponent)).get();
        // Apply the correction to the position based on the vector and layout origin
        return new Coord2D(
                pos.x() * versor.xComponent() + layoutOrigin.x(),
                pos.y() * versor.yComponent() + layoutOrigin.y());
    }
}
