package com.project.paradoxplatformer.controller;

/**
 * Defines a basic interface for a controller that manages and centralizes model and view events or state changes.
 * <p>
 * The {@code Controller} interface is responsible for handling events and changes in state from both the model
 * and view components of the application. It acts as the central point of control, ensuring that all updates and
 * state changes are processed consistently. The controller is designed to be independent of both view and model
 * implementations, making it suitable for integration into the main application logic.
 * </p>
 * <p>
 * This interface typically serves as the entry point for starting and stopping the controller's operations, and it
 * manages the lifecycle of associated threads or processes.
 * </p>
 */
public interface Controller {

    /**
     * Starts the controller.
     * <p>
     * This method initializes the controller and begins its operation. It typically involves setting up necessary
     * resources, initializing components, and starting any background threads or processes required for the controller
     * to function properly.
     * </p>
     * <p>
     * After calling this method, the controller should be ready to handle events and manage state changes as required
     * by the application.
     * </p>
     */
    void start();

    /**
     * Quits the controller, terminating all associated threads and processes.
     * <p>
     * This method stops the controller and cleans up any resources or threads that were started during its operation.
     * It ensures that all background activities are properly shut down and that the controller is safely terminated.
     * </p>
     * <p>
     * This method is typically called when the application is closing or when the controller needs to be stopped
     * gracefully.
     * </p>
     */
    void quit();
}

