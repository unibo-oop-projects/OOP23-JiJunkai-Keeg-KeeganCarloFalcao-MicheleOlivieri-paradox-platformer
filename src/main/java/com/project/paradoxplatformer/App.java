package com.project.paradoxplatformer;

import com.project.paradoxplatformer.controller.SimpleController;
import com.project.paradoxplatformer.view.legacy.ViewFramework;

/**
 * The entry point of the Paradox Platformer application.
 * <p>
 * This class contains the main method which initializes and starts the
 * application.
 * It creates an instance of {@link SimpleController} with a {@link ViewFramework}
 * factory
 * for JavaFX and the application name "Paradox Platformer". The controller is
 * then
 * started to launch the application.
 * </p>
 */
public final class App {

    // Private constructor to prevent instantiation
    private App() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }

    /**
     * The main method that serves as the entry point of the application.
     * <p>
     * This method creates an instance of {@link SimpleController} with the view
     * factory from {@link ViewFramework} and the application title "Paradox
     * Platformer".
     * It then starts the controller to begin the application's execution.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(final String[] args) {
        new SimpleController<>(ViewFramework.javaFxFactory(), "Paradox Platformer").start();
    }
}
