package com.project.paradoxplatformer.utils.geometries.orientations;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * {@code GraphicOffsetCorrector} is a final implementation of the {@link OffsetCorrector} interface 
 * designed to adjust the position of graphical elements based on a combination of vector direction (versor), 
 * inner layout box offset, and outer layout offset. It calculates the corrected coordinates for an element 
 * within a given layout by applying transformations to its original position.
 * 
 * <p>This class is intended for use in graphical applications where 2D components need to be adjusted or 
 * transformed according to a particular layout or alignment scheme. It combines multiple offset strategies, 
 * including both inner and outer layout corrections, and modifies the element's coordinates by applying 
 * directional scaling with a vector (versor).
 * 
 * <p>Since this class is marked {@code final}, it cannot be subclassed, ensuring its behavior remains 
 * consistent across usage.
 * 
 * <p><b>Example Usage:</b>
 * <pre>{@code
 * Offset outerLayout = ...;   // Define the outer layout offset
 * BoxOffset<Dimension> boxOffset = ...;  // Define the inner box offset
 * Vector2D versor = new Vector2D(1.0, 1.0); // Define the directional versor
 * 
 * GraphicOffsetCorrector corrector = new GraphicOffsetCorrector(outerLayout, boxOffset, versor);
 * Dimension componentDimension = new Dimension(200, 100);
 * Coord2D originalPos = new Coord2D(50, 30);
 * Coord2D correctedPos = corrector.correct(componentDimension, originalPos);
 * }</pre>
 * 
 * <p>In the above example, the {@code corrector} object calculates the corrected position of a graphical 
 * component based on the provided outer layout, inner layout, and direction vector.
 *
 * @see OffsetCorrector
 * @see Vector2D
 * @see Coord2D
 * @see Dimension
 * 
 */
public final class GraphicOffsetCorrector implements OffsetCorrector {

    /** 
     * The direction vector (versor) used for adjusting the x and y components of the position.
     * It provides scaling factors for each axis, enabling coordinate transformations based on the
     * vector's components (usually (-1, 1) or (1, -1))
     */
    private final Vector2D versor;

    /** 
     * The offset for the inner layout box, which determines how the graphical component is positioned
     * within its layout region.
     */
    private final BoxOffset<Dimension> innerLayoutBoxOffset;

    /** 
     * The outer layout offset that determines the base positioning anchor of the graphical component 
     * within the overall layout. It serves as the primary offset reference from which the inner layout 
     * offset is applied.
     */
    private final Offset outerLayoutOffset;

    /**
     * Constructs a new {@code GraphicOffsetCorrector} with the given layout offset, box offset, and versor.
     * 
     * <p>This constructor initializes the necessary parameters for calculating the corrected coordinates. 
     * The outer layout offset defines the base anchor position, the inner layout box offset allows for 
     * layout-specific adjustments, and the versor provides directional scaling for the coordinate correction.
     * 
     * @param layout the {@link Offset} object representing the outer layout offset, providing an anchor for positioning.
     * @param box the {@link BoxOffset} object representing the inner layout box offset, allowing for inner layout adjustments.
     * @param versor the {@link Vector2D} object representing the directional vector used for scaling the position's 
     * x and y components.
     * 
     * @throws NullPointerException if any of the provided parameters are {@code null}.
     */
    public GraphicOffsetCorrector(final Offset layout, final BoxOffset<Dimension> box, final Vector2D versor) {
        this.outerLayoutOffset = layout;
        this.innerLayoutBoxOffset = box;
        this.versor = versor;
    }

    /**
     * Corrects the position of a graphical element within a layout based on its dimensions and initial coordinates.
     * 
     * <p>This method calculates the corrected coordinates by applying the following steps:
     * <ol>
     *   <li>Evaluate the {@code innerLayoutBoxOffset} based on the provided component dimensions 
     * ({@code gComponent}).</li>
     *   <li>Use the {@code outerLayoutOffset} to determine the layout's origin anchor point.</li>
     *   <li>Adjust the original element's position by scaling its x and y coordinates with the components 
     * of the {@code versor}.</li>
     *   <li>Translate the scaled position to the layout's anchor point to get the final corrected coordinates.</li>
     * </ol>
     * 
     * @param compDimension the {@link Dimension} object representing the width and height of the graphical component.
     * @param pos the {@link Coord2D} object representing the original coordinates of the graphical component.
     * @return a new {@link Coord2D} object representing the corrected coordinates after applying the offset and versor 
     * transformations.
     * 
     * @throws NullPointerException if either {@code gComponent} or {@code pos} is {@code null}.
     */
    @Override
    public Coord2D correct(final Dimension compDimension, final Coord2D pos) {
        // Calculate the origin of the layout using the outer layout offset and inner box offset.
        var layoutOrigin = this.outerLayoutOffset.anchor(this.innerLayoutBoxOffset.evaluate(compDimension)).get();
        // Return the corrected coordinates by applying scaling with the versor and translation with the layout origin.
        return new Coord2D(
                pos.x() * versor.xComponent() + layoutOrigin.x(),
                pos.y() * versor.yComponent() + layoutOrigin.y());
    }
}

