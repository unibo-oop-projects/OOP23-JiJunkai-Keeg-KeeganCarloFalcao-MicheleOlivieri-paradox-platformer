package com.project.paradoxplatformer.view;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ElementDecorator {

    // Applies styles to the settings button (e.g., background color, border, etc.)
    public static void decorateSettingsButton(Button button) {
        button.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: white; -fx-font-weight: bold;");
    }

    // Applies a color to the level circle
    public static void decorateLevelCircle(Circle circle, Color fillColor) {
        circle.setFill(fillColor);
        circle.setStroke(Color.BLACK); // Add a black border for visibility
        circle.setStrokeWidth(2.0);
    }
}
