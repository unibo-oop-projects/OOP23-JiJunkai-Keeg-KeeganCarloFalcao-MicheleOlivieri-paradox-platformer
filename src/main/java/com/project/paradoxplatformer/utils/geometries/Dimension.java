package com.project.paradoxplatformer.utils.geometries;

public record Dimension(double width, double height) {

    public static Dimension dot() {
        return new Dimension(0, 0);
    }
}
