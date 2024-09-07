package com.project.paradoxplatformer.controller;

/**
 * A basic Controller interface.
 * Note that it is not coupled with view or model. Usually it's called in main
 * TO BE DONE, NOT FINISHED YET
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
