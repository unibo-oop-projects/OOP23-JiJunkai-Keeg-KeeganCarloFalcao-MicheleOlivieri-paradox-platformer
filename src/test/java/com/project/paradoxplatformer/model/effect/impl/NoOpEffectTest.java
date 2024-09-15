package com.project.paradoxplatformer.model.effect.impl;

import com.project.paradoxplatformer.model.trigger.Button;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

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
        mockGameObject = new Button(new Coord2D(0, 0), new Dimension(0, 0));
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
