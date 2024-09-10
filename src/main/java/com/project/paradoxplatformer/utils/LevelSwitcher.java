package com.project.paradoxplatformer.utils;

@FunctionalInterface
public interface LevelSwitcher {
    void switchLevel(Level level) throws InvalidResourceException;
}
