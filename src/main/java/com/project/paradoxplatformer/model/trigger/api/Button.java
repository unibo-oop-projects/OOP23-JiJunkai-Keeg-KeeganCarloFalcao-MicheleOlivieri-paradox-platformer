package com.project.paradoxplatformer.model.trigger.api;

import com.project.paradoxplatformer.model.trigger.AbstractTrigger;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public class Button extends AbstractTrigger {

    public Button(Coord2D position, Dimension dimension) {
        this.position = position;
        this.dimension = dimension;
    }

    public Button() {
        this.position = new Coord2D(100, 200);
        this.dimension = new Dimension(50, 50);
    }

}
