package com.project.paradoxplatformer.view.fxmlstyle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleStyleBuilder {
    private final Circle circle;

    public CircleStyleBuilder(Circle circle) {
        this.circle = circle;
    }

    public CircleStyleBuilder withFillColor(Color color) {
        circle.setFill(color);
        return this;
    }

    public CircleStyleBuilder withStrokeColor(Color color) {
        circle.setStroke(color);
        return this;
    }

    public CircleStyleBuilder withStrokeWidth(double width) {
        circle.setStrokeWidth(width);
        return this;
    }

    public void apply() {
    }
}
