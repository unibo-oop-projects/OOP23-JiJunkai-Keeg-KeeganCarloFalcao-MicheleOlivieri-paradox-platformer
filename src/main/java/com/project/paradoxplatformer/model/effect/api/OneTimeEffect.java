package com.project.paradoxplatformer.model.effect.api;

/**
 * Represents an effect that is applied only once.
 */
public interface OneTimeEffect extends Effect {

    /**
     * Indicates that this effect is a one-time effect.
     * 
     * @return true, since this effect should be applied only once.
     */
    @Override
    default boolean isOneTimeEffect() {
        return true;
    }

    /**
     * Returns null, as one-time effects cannot be recreated.
     * 
     * @return null.
     */
    @Override
    default Effect recreate() {
        return null;
    }
}
