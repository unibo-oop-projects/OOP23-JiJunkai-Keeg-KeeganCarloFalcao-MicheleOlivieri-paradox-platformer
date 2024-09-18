package com.project.paradoxplatformer.model.effect.impl;

import com.project.paradoxplatformer.model.trigger.Button;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the NoOpEffect class, ensuring its behavior matches the
 * expected implementation.
 */
class NoOpEffectTest {

    private NoOpEffect noOpEffect;
    private Button mockGameObject;

    /**
     * Initializes the NoOpEffect and Button instances before each test.
     */
    @BeforeEach
    void setUp() {
        noOpEffect = new NoOpEffect();
        mockGameObject = new Button(0, new Coord2D(0, 0), new Dimension(0, 0));
    }

    /**
     * Tests that the NoOpEffect completes immediately when applied to a game
     * object.
     */
    @SuppressFBWarnings(value = "UwF", justification = "Fields are initialized in @BeforeEach method before usage.")
    @Test
    void testApplyToGameObject() {
        final CompletableFuture<Void> future = noOpEffect.apply(Optional.of(mockGameObject), Optional.empty());
        assertTrue(future.isDone(), "The NoOpEffect should complete immediately.");
    }

    /**
     * Tests that the NoOpEffect is identified as a one-time effect.
     */
    @SuppressFBWarnings(value = "UwF", justification = "Fields are initialized in @BeforeEach method before usage.")
    @Test
    void testIsOneTimeEffect() {
        assertTrue(noOpEffect.isOneTimeEffect(), "NoOpEffect should be a one-time effect.");
    }

    /**
     * Tests that the NoOpEffect's recreate method returns null.
     */
    @SuppressFBWarnings(value = "UwF", justification = "Fields are initialized in @BeforeEach method before usage.")
    @Test
    void testRecreateReturnsNull() {
        assertTrue(noOpEffect.recreate() == null, "NoOpEffect should return null when recreate is called.");
    }
}
