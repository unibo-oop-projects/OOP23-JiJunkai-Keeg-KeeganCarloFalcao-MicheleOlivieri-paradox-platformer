package com.project.paradoxplatformer.controller.gameloop;

/**
 * A GameLoop interface is commonly used in game developement, it allows to define what is
 * going to change at every frame. A proper implementation must change the state of
 * every mutable object which is used within the loop.
 * Usually such method should be on a separate thread, allowing async inputs work
 * @author Keegan Carlo Falcao
 */
@FunctionalInterface
public interface GameLoop {
    /**
     * Usually an infinite loop refreshed every frame.
     * @param dt time elapsed between last frame and current
     */
    void loop(long dt);
}
