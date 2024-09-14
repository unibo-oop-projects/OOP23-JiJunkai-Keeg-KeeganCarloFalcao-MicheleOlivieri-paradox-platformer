package com.project.paradoxplatformer.model.effect;

import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.model.effect.impl.NoOpEffect;
import com.project.paradoxplatformer.model.trigger.api.Button;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class EffectHandlerTest {

    private EffectHandler effectHandler;

    @BeforeEach
    void setUp() {
        effectHandler = new EffectHandler();
    }

    @Test
    void testAddAndApplyCollisionEffectsForType() {
        // Setup
        CollisionType type = CollisionType.BUTTON;
        Supplier<Effect> effectSupplier = NoOpEffect::new;

        // Add effects
        effectHandler.addCollisionEffectsForType(type, effectSupplier);

        // Create test objects
        Button source = new Button();
        Button target = new Button();

        // Apply effects
        CompletableFuture<Void> result = effectHandler.applyEffects(source, target);

        // Verify
        assertDoesNotThrow(() -> result.join(), "Effect application should not throw an exception.");
    }

    @Test
    void testAddAndApplyCollisionEffectsForObject() {
        // Setup
        CollisionType type = CollisionType.BUTTON;
        Button object = new Button();
        Supplier<Effect> effectSupplier = NoOpEffect::new;

        // Add effects
        effectHandler.addCollisionEffectsForObject(type, object, effectSupplier);

        // Create test objects
        Button source = new Button();

        // Apply effects
        CompletableFuture<Void> result = effectHandler.applyEffects(source, object);

        // Verify
        assertDoesNotThrow(() -> result.join(), "Effect application should not throw an exception.");
    }

    @Test
    void testGetAllEffects() {
        // Setup
        CollisionType type = CollisionType.BUTTON;
        Button object = new Button();
        effectHandler.addCollisionEffectsForObject(type, object, NoOpEffect::new);

        // Get all effects
        ChainOfEffects chain = effectHandler.getAllEffects(object);

        // Verify
        assertNotNull(chain, "Chain of effects should not be null.");
        assertFalse(chain.getEffects().isEmpty(), "Chain of effects should not be empty.");
    }

    @Test
    void testResetEffects() {
        // Setup
        CollisionType type = CollisionType.BUTTON;
        Button object = new Button();
        effectHandler.addCollisionEffectsForObject(type, object, NoOpEffect::new);

        // Reset effects
        effectHandler.reset(object, type);

        // Verify
        ChainOfEffects chain = effectHandler.getAllEffects(object);
        assertTrue(chain.getEffects().isEmpty(), "Chain of effects should be empty after reset.");
    }

}
