package com.project.paradoxplatformer.controller;

/**
 * A basic Controller interface with the purpose of centralizing every model and view event or change of state.
 * So all these events/changes must meet the controller cause it has the access to modify the game Update State
 * Note that it is not coupled with view or model. Usually it's called in main
 */
public interface Controller {

    /**
     * Starts the controller.
     */
    void start();

    /**
     * Quits the controller, so all threads must end.
     */
    void quit();

}
