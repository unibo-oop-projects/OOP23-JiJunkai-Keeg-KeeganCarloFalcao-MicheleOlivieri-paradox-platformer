package com.project.paradoxplatformer.controller.gameloop;

/**
 * TaskLoopFactory creates different threads based on avaiable View loops
 */
public interface TaskLoopFactory {
    /**
     * To use only if view is on a JavaFX Thread
     * @return {@code ObservableLoopManager}
     */
    ObservableLoopManager animationLoop();

    /**
     * Common thread for every view, a bit slower then the animationLoop
     * @return {@code LoopManager}
     */
    LoopManager threadLoop();
}
