package com.project.paradoxplatformer.controller.gameloop;

/**
 * LoopManager as the name says serves to manage a looping thread.
 */
public interface LoopManager {

    /**
     * Starts the {@code LoopManager}.
     * {@link #start()}
     */
    void start();

    /**
     * Stops the {@code LoopManager}.
     * {@link #stop()}
     */
    void stop();

    /**
     * Check wether the {@code LoopManager} is running.
     * {@link #isRunning()}
     * 
     * @return the running state of the loop.
     * 
     */
    boolean isRunning();
}
