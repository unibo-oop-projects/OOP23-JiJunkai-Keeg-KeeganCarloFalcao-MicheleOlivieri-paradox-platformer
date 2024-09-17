package com.project.paradoxplatformer.model.effect;

import com.project.paradoxplatformer.controller.games.Level;

/**
 * Factory interface for creating instances of {@link EffectHandler}.
 * Provides methods to get a default effect handler and a level-specific effect
 * handler.
 */
public interface EffectHandlerFactory {

    /**
     * Creates and returns the default {@link EffectHandler}.
     *
     * @return the default effect handler
     */
    EffectHandler defaultEffectHandler();

    /**
     * Creates and returns an {@link EffectHandler} specific to the given level.
     *
     * @param level the level for which to create the effect handler
     * @return the effect handler for the specified level
     */
    EffectHandler getEffectHandlerForLevel(Level level);
}
