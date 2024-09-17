package com.project.paradoxplatformer.model.effect;

import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.model.effect.impl.NoOpEffect;
import com.project.paradoxplatformer.model.trigger.Button;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

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
        Button source = new Button(0, new Coord2D(0, 0), new Dimension(0, 0));
        Button target = new Button(1, new Coord2D(0, 0), new Dimension(0, 0));

        // Apply effects
        CompletableFuture<Void> result = effectHandler.applyEffects(source, target);

        // Verify
        assertDoesNotThrow(() -> result.join(), "Effect application should not throw an exception.");
    }

    @Test
    void testAddAndApplyCollisionEffectsForObject() {
        // Setup
        CollisionType type = CollisionType.BUTTON;
        Button object = new Button(0, new Coord2D(0, 0), new Dimension(0, 0));
        Supplier<Effect> effectSupplier = NoOpEffect::new;

        // Add effects
        effectHandler.addCollisionEffectsForObject(type, object, effectSupplier);

        // Create test objects
        Button source = new Button(1, new Coord2D(0, 0), new Dimension(0, 0));

        // Apply effects
        CompletableFuture<Void> result = effectHandler.applyEffects(source, object);

        // Verify
        assertDoesNotThrow(() -> result.join(), "Effect application should not throw an exception.");
    }

    @Test
    void testGetAllEffects() {
        // Setup
        CollisionType type = CollisionType.BUTTON;
        Button object = new Button(1, new Coord2D(0, 0), new Dimension(0, 0));
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
        Button object = new Button(1, new Coord2D(0, 0), new Dimension(0, 0));
        effectHandler.addCollisionEffectsForObject(type, object, NoOpEffect::new);

        // Reset effects
        effectHandler.reset(object, type);

        // Verify
        ChainOfEffects chain = effectHandler.getAllEffects(object);
        assertTrue(chain.getEffects().isEmpty(), "Chain of effects should be empty after reset.");
    }

}
