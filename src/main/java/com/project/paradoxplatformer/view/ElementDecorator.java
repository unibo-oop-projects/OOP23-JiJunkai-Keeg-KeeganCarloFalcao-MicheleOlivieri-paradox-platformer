package com.project.paradoxplatformer.view;

import com.project.paradoxplatformer.view.fxmlstyle.ButtonStyleBuilder;
import com.project.paradoxplatformer.view.fxmlstyle.CircleStyleBuilder;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ElementDecorator {

    /**
     * Applies styles to a Button using the ButtonStyleBuilder.
     * 
     * @param button The Button to be styled.
     */
    public static void decorateSettingsButton(Button button) {
        new ButtonStyleBuilder(button)
                .withBackgroundColor("#1e90ff")
                .withTextColor("white")
                .withFontWeight("bold")
                .apply();
    }

    /**
     * Applies styles to a Circle using the CircleStyleBuilder.
     * 
     * @param circle    The Circle to be styled.
     * @param fillColor The color to fill the Circle.
     */
    public static void decorateLevelCircle(Circle circle, Color fillColor) {
        new CircleStyleBuilder(circle)
                .withFillColor(fillColor)
                .withStrokeColor(Color.BLACK)
                .withStrokeWidth(2.0)
                .apply();
    }
}
