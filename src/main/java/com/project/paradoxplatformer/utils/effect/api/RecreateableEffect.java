package com.project.paradoxplatformer.utils.effect.api;

public interface RecreateableEffect extends Effect {
    @Override
    Effect recreate();
}
