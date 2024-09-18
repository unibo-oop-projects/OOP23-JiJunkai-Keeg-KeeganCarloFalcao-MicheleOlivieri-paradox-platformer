package com.project.paradoxplatformer.model.effect.api;

import com.project.paradoxplatformer.model.effect.impl.NoOpEffect;
import com.project.paradoxplatformer.model.trigger.Button;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EffectTest {

    private Effect mockEffect;
    private CollidableGameObject mockGameObject;

    @BeforeEach
    void setUp() {
        mockEffect = new NoOpEffect();
        mockGameObject = new Button(0, new Coord2D(0, 0), new Dimension(0, 0));
    }

    @Test
    @SuppressFBWarnings(value = "UwF", justification = "Fields are initialized in @BeforeEach method before usage.")
    void testApply() {
        final CompletableFuture<Void> future = mockEffect.apply(Optional.of(mockGameObject), Optional.empty());
        assertTrue(future != null, "The apply method should return a non-null CompletableFuture.");
    }

    @Test
    @SuppressFBWarnings(value = "UwF", justification = "Fields are initialized in @BeforeEach method before usage.")
    void testIsOneTimeEffect() {
        assertTrue(mockEffect.isOneTimeEffect(), "Effect should indicate if it's a one-time effect.");
    }

    @Test
    @SuppressFBWarnings(value = "UwF", justification = "Fields are initialized in @BeforeEach method before usage.")
    void testRecreateDefaultBehavior() {
        assertTrue(mockEffect.recreate() == null,
                "The recreate method should return null.");
    }
}
