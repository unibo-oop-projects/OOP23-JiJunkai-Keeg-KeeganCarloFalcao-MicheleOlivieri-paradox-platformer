package com.project.paradoxplatformer.utils.effect.impl;

import com.project.paradoxplatformer.model.trigger.api.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NoOpEffectTest {

    private NoOpEffect noOpEffect;
    private Button mockGameObject;

    @BeforeEach
    void setUp() {
        noOpEffect = new NoOpEffect();
        mockGameObject = new Button();
    }

    @Test
    void testApplyToGameObject() {
        CompletableFuture<Void> future = noOpEffect.apply(Optional.of(mockGameObject), Optional.empty());
        assertTrue(future.isDone(), "The NoOpEffect should complete immediately.");
    }

    @Test
    void testIsOneTimeEffect() {
        assertTrue(noOpEffect.isOneTimeEffect(), "NoOpEffect should be a one-time effect.");
    }

    @Test
    void testRecreateReturnsNull() {
        assertTrue(noOpEffect.recreate() == null, "NoOpEffect should return null when recreate is called.");
    }
}
