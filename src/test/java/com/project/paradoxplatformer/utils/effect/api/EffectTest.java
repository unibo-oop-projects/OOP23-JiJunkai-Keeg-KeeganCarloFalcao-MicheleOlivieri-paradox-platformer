package com.project.paradoxplatformer.utils.effect.api;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.model.trigger.api.Button;
import com.project.paradoxplatformer.utils.effect.api.Effect;
import com.project.paradoxplatformer.utils.effect.impl.NoOpEffect;

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
        mockGameObject = new Button();
    }

    @Test
    void testApply() {
        CompletableFuture<Void> future = mockEffect.apply(Optional.of(mockGameObject), Optional.empty());
        assertTrue(future != null, "The apply method should return a non-null CompletableFuture.");
    }

    @Test
    void testIsOneTimeEffect() {
        assertTrue(mockEffect.isOneTimeEffect(), "Effect should indicate if it's a one-time effect.");
    }

    @Test
    void testRecreateDefaultBehavior() {
        assertTrue(mockEffect.recreate() == null,
                "The recreate method should return null.");
    }
}
