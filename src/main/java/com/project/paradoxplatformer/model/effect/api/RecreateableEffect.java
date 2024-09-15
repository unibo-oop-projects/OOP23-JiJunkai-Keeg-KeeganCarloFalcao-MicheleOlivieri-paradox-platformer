package com.project.paradoxplatformer.model.effect.api;

/**
 * Represents an effect that can be recreated.
 */
public interface RecreateableEffect extends Effect {

    /**
     * Creates a new instance of this effect.
     * 
     * @return A new instance of this effect.
     */
    @Override
    Effect recreate();
}
