package com.project.paradoxplatformer.utils.geometries.coordinates;

public final record Coord2D(double x, double y) {

    public static Coord2D origin() {
        return new Coord2D(0, 0);
    }   
}
