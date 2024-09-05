package com.project.paradoxplatformer.view;

import com.project.paradoxplatformer.view.fxmlstyle.ButtonStyleBuilder;
import com.project.paradoxplatformer.view.fxmlstyle.CircleStyleBuilder;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ElementDecorator {

    // Applies styles to the settings button using the ButtonStyleBuilder
    public static void decorateSettingsButton(Button button) {
        new ButtonStyleBuilder(button)
                .withBackgroundColor("#1e90ff")
                .withTextColor("white")
                .withFontWeight("bold")
                .apply();
    }

    // Applies styles to the level circle using the CircleStyleBuilder
    public static void decorateLevelCircle(Circle circle, Color fillColor) {
        new CircleStyleBuilder(circle)
                .withFillColor(fillColor)
                .withStrokeColor(Color.BLACK)
                .withStrokeWidth(2.0)
                .apply();
    }
}
