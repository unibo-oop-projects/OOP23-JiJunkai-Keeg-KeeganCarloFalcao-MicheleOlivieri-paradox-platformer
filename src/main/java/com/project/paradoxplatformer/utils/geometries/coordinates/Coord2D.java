package com.project.paradoxplatformer.utils.geometries.coordinates;

public final record Coord2D(double x, double y) {

    public static Coord2D origin() {
        return new Coord2D(0, 0);
    }

    public static Coord2D randomX(double fixedY) {
        return new Coord2D(randomInt(0, 500), fixedY);
    }

    public static Coord2D randomY(double fixedX) {
        return new Coord2D(fixedX, randomInt(0, 500));
    }

    private static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
