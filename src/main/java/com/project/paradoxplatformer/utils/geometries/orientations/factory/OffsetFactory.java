package com.project.paradoxplatformer.utils.geometries.orientations.factory;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.orientations.BoxOffset;
import com.project.paradoxplatformer.utils.geometries.orientations.Offset;

/**
 * factory
 */
public interface OffsetFactory {
    /**
     * top left 
     * @return {@code Offset}
     */
    Offset topLeft();

    /**
     * bottom right
     * @return {@code Offset}
     */
    Offset bottomRight();

    /**
     * bottom left
     * @return {@code Offset}
     */
    Offset bottomLeft();

    /**
     * top right
     * @return {@code Offset}
     */
    Offset topRight();

    /**
     * inner layout offset
     *  
     * @return {@code BoxOffset<Dimension>}
    */
    BoxOffset<Dimension> boxOffset();
}
