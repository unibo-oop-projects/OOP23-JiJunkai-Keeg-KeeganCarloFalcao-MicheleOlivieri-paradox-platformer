package com.project.paradoxplatformer.model.effect;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.model.effect.api.EffectHandler;
import com.project.paradoxplatformer.model.effect.impl.EffectHandlerImpl;
import com.project.paradoxplatformer.model.effect.impl.NoOpEffect;
import com.project.paradoxplatformer.model.effect.managers.ChainOfEffects;
import com.project.paradoxplatformer.model.trigger.Button;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

class EffectHandlerTest {

    private EffectHandler effectHandler;

    @BeforeEach
    void setUp() {
        effectHandler = new EffectHandlerImpl();
    }

    @Test
    void testAddAndApplyCollisionEffectsForType() {
        // Setup
        final CollisionType type = CollisionType.BUTTON;
        final Supplier<Effect> effectSupplier = NoOpEffect::new;

        // Add effects
        effectHandler.addCollisionEffectsForType(type, effectSupplier);

        // Create test objects
        final Button source = new Button(0, new Coord2D(0, 0), new Dimension(0, 0));
        final Button target = new Button(1, new Coord2D(0, 0), new Dimension(0, 0));

        // Apply effects
        final CompletableFuture<Void> result = effectHandler.applyEffects(source, target);

        // Verify
        assertDoesNotThrow(result::join, "Effect application should not throw an exception.");
    }

    @Test
    void testAddAndApplyCollisionEffectsForObject() {
        // Setup
        final CollisionType type = CollisionType.BUTTON;
        final Button object = new Button(0, new Coord2D(0, 0), new Dimension(0, 0));
        final Supplier<Effect> effectSupplier = NoOpEffect::new;

        // Add effects
        effectHandler.addCollisionEffectsForObject(type, object, effectSupplier);

        // Create test objects
        final Button source = new Button(1, new Coord2D(0, 0), new Dimension(0, 0));

        // Apply effects
        final CompletableFuture<Void> result = effectHandler.applyEffects(source, object);

        // Verify
        assertDoesNotThrow(result::join, "Effect application should not throw an exception.");
    }

    @Test
    void testGetAllEffects() {
        // Setup
        final CollisionType type = CollisionType.BUTTON;
        final Button object = new Button(1, new Coord2D(0, 0), new Dimension(0, 0));
        effectHandler.addCollisionEffectsForObject(type, object, NoOpEffect::new);

        // Get all effects
        final ChainOfEffects chain = effectHandler.getAllEffects(object);

        // Verify
        assertNotNull(chain, "Chain of effects should not be null.");
        assertFalse(chain.getEffects().isEmpty(), "Chain of effects should not be empty.");
    }

    @Test
    void testResetEffects() {
        // Setup
        final CollisionType type = CollisionType.BUTTON;
        final Button object = new Button(1, new Coord2D(0, 0), new Dimension(0, 0));
        effectHandler.addCollisionEffectsForObject(type, object, NoOpEffect::new);

        // Reset effects
        effectHandler.reset(object, type);

        // Verify
        final ChainOfEffects chain = effectHandler.getAllEffects(object);
        assertTrue(chain.getEffects().isEmpty(), "Chain of effects should be empty after reset.");
    }
}
