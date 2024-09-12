package com.project.paradoxplatformer.view;

import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

public class EventBinder {

    /**
     * Binds an event handler to a Button.
     * 
     * @param settingsButton  The Button to bind the handler to.
     * @param onSettingsClick The action to perform on button click.
     */
    public static void bindButtons(Button settingsButton, Runnable onSettingsClick) {
        settingsButton.setOnAction(event -> onSettingsClick.run());
    }

    /**
     * Binds an event handler to a Circle.
     * 
     * @param circle        The Circle to bind the handler to.
     * @param onCircleClick The action to perform on circle click.
     */
    public static void bindLevelCircle(Circle circle, Runnable onCircleClick) {
        circle.setOnMouseClicked(event -> onCircleClick.run());
    }
}
