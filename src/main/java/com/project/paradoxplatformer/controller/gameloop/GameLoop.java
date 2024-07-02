package com.project.paradoxplatformer.controller.gameloop;

@FunctionalInterface
public interface GameLoop {
    void loop(final long dt);
}
