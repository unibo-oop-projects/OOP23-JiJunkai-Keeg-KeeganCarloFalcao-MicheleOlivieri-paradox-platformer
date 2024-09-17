package com.project.paradoxplatformer.model.effect.api;

import com.project.paradoxplatformer.controller.games.Level;

/**
 * An interface for creating instances of {@link EffectHandler}.
 * Provides method to get a level-specific effect handler.
 */
public interface EffectHandlerFactory {

    /**
     * Creates and returns an {@link EffectHandler} specific to the given level.
     *
     * @param level the level for which to create the effect handler
     * @return the effect handler for the specified level or the default one
     */
    EffectHandler getEffectHandlerForLevel(Level level);
}
