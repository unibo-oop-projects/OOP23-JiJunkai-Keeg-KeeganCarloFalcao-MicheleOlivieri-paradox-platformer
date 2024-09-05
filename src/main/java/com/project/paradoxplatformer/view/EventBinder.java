package com.project.paradoxplatformer.view;

import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

public class EventBinder {

    // Binds event to the Settings button
    public static void bindSettingsButton(Button settingsButton, Runnable onSettingsClick) {
        settingsButton.setOnAction(event -> onSettingsClick.run());
    }

    // Binds event to a Circle shape for different level navigation
    public static void bindLevelCircle(Circle circle, Runnable onCircleClick) {
        circle.setOnMouseClicked(event -> onCircleClick.run());
    }
}
