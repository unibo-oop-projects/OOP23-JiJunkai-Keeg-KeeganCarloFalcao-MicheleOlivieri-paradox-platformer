package com.project.paradoxplatformer.utils.effect.api;

public interface OneTimeEffect extends Effect {
    @Override
    default boolean isOneTimeEffect() {
        return true;
    }

    @Override
    default Effect recreate() {
        return null;
    }
}
