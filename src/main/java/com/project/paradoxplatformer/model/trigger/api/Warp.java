package com.project.paradoxplatformer.model.trigger.api;

import com.project.paradoxplatformer.model.trigger.AbstractTrigger;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public class Warp extends AbstractTrigger {

    private final Coord2D destination;

    public Warp(double destinationX, double destinationY) {
        this.destination = new Coord2D(destinationX, destinationY);
    }

    public Warp(Coord2D destination) {
        this.destination = destination;
    }

    public Warp() {
        this.position = new Coord2D(100, 200);
        this.dimension = new Dimension(50, 50);
        this.destination = null;
    }

}
