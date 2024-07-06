package com.project.paradoxplatformer.utils.geometries.orientations.factory;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.orientations.BoxOffset;
import com.project.paradoxplatformer.utils.geometries.orientations.Offset;

public interface OffsetFactory {
    
    Offset topLeft();

    Offset bottomRight();

    Offset bottomLeft();

    Offset topRight();

    BoxOffset<Dimension> boxOffset();
}
