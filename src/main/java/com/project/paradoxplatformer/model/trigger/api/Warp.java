package com.project.paradoxplatformer.model.trigger.api;

import com.project.paradoxplatformer.model.trigger.AbstractTrigger;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public class Warp extends AbstractTrigger {

    public Warp(Coord2D position, Dimension dimension) {
        super(position, dimension);
        this.position = position;
        this.dimension = dimension;
    }

    public Warp() {
        super(new Coord2D(100, 200), new Dimension(50, 50));
    }

}
