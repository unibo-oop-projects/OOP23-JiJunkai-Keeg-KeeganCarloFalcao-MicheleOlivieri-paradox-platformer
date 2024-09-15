package com.project.paradoxplatformer;

import com.project.paradoxplatformer.controller.SimpleController;
import com.project.paradoxplatformer.view.legacy.ViewLegacy;

/**
 * The entry point of the Paradox Platformer application.
 * <p>
 * This class contains the main method which initializes and starts the
 * application.
 * It creates an instance of {@link SimpleController} with a {@link ViewLegacy}
 * factory
 * for JavaFX and the application name "Paradox Platformer". The controller is
 * then
 * started to launch the application.
 * </p>
 */
public class App {

    /**
     * The main method that serves as the entry point of the application.
     * <p>
     * This method creates an instance of {@link SimpleController} with the view
     * factory from {@link ViewLegacy} and the application title "Paradox
     * Platformer".
     * It then starts the controller to begin the application's execution.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new SimpleController<>(ViewLegacy.javaFxFactory(), "Paradox Platformer").start();
    }
}
