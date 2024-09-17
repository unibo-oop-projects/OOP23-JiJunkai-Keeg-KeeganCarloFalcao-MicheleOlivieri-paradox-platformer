package com.project.paradoxplatformer.view.fxmlstyle.decorator;

import com.project.paradoxplatformer.view.fxmlstyle.builder.ButtonStyleBuilder;
import com.project.paradoxplatformer.view.fxmlstyle.builder.CircleStyleBuilder;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Provides utility methods for applying styles to UI elements.
 * Utilizes builder patterns for styling Button and Circle elements.
 */
public final class ElementDecorator {

    // Private constructor to prevent instantiation
    private ElementDecorator() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    /**
     * Applies a predefined set of styles to a Button.
     * Uses the ButtonStyleBuilder to set the background color, text color, and font
     * weight.
     *
     * @param button The Button to be styled.
     */
    public static void decorateSettingsButton(final Button button) {
        new ButtonStyleBuilder(button)
                .withBackgroundColor("#1e90ff") // Sets background color to a shade of blue
                .withTextColor("white") // Sets text color to white
                .withFontWeight("bold") // Sets font weight to bold
                .apply(); // Applies the styles to the Button
    }

    /**
     * Applies a predefined set of styles to a Circle.
     * Uses the CircleStyleBuilder to set the fill color, stroke color, and stroke
     * width.
     *
     * @param circle    The Circle to be styled.
     * @param fillColor The color to fill the Circle.
     */
    public static void decorateLevelCircle(final Circle circle, final Color fillColor) {
        new CircleStyleBuilder(circle)
                .withFillColor(fillColor) // Sets the fill color of the Circle
                .withStrokeColor(Color.BLACK) // Sets the stroke color to black
                .withStrokeWidth(2.0) // Sets the stroke width to 2.0
                .apply(); // Applies the styles to the Circle
    }
}
