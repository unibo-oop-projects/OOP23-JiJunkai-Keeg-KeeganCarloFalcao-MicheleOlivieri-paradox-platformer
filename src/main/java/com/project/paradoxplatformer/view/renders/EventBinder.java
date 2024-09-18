package com.project.paradoxplatformer.view.renders;

import javafx.scene.control.Button;

/**
 * Provides utility methods for binding event handlers to UI elements.
 * Specifically handles event bindings for Buttons and Circles.
 */
public final class EventBinder {

    // Private constructor to prevent instantiation
    private EventBinder() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    /**
     * Binds an event handler to a Button.
     * The provided action is executed when the Button is clicked.
     *
     * @param button          The Button to bind the handler to.
     * @param onSettingsClick The action to perform when the Button is clicked.
     */
    public static void bindButtons(final Button button, final Runnable onSettingsClick) {
        button.setOnAction(event -> onSettingsClick.run()); // Binds the click event to the provided action
    }
}
