package com.project.paradoxplatformer.view;

import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

/**
 * Provides utility methods for binding event handlers to UI elements.
 * Specifically handles event bindings for Buttons and Circles.
 */
public class EventBinder {

    /**
     * Binds an event handler to a Button.
     * The provided action is executed when the Button is clicked.
     *
     * @param settingsButton  The Button to bind the handler to.
     * @param onSettingsClick The action to perform when the Button is clicked.
     */
    public static void bindButtons(Button settingsButton, Runnable onSettingsClick) {
        settingsButton.setOnAction(event -> onSettingsClick.run()); // Binds the click event to the provided action
    }

    /**
     * Binds an event handler to a Circle.
     * The provided action is executed when the Circle is clicked.
     *
     * @param circle        The Circle to bind the handler to.
     * @param onCircleClick The action to perform when the Circle is clicked.
     */
    public static void bindLevelCircle(Circle circle, Runnable onCircleClick) {
        circle.setOnMouseClicked(event -> onCircleClick.run()); // Binds the mouse click event to the provided action
    }
}
