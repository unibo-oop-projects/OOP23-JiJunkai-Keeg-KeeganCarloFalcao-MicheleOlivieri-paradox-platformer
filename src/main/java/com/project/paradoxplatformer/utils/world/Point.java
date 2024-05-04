package com.project.paradoxplatformer.utils.world;

public record Point(double x, double y) {

    public Point sum(Vector p){
        return new Point(x+ p.x(), y+p.y());
    }

}