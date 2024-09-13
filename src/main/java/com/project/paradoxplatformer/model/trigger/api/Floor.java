package com.project.paradoxplatformer.model.trigger.api;

import com.project.paradoxplatformer.model.trigger.AbstractTrigger;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public class Floor extends AbstractTrigger {

    protected Floor(Coord2D position, Dimension dimension) {
        super(position, dimension);
    }

}
