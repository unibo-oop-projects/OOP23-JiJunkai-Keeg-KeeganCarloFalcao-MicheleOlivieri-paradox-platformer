package com.project.paradoxplatformer.utils.world;

public record Coord2D(double x, double y) {

    public Coord2D sum(Vector p){
        return new Coord2D(x+ p.x(), y+p.y());
    }

}