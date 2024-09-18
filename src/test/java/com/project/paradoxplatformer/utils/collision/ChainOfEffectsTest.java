package com.project.paradoxplatformer.utils.collision;

import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.model.effect.impl.NoOpEffect;
import com.project.paradoxplatformer.model.effect.managers.ChainOfEffects;
import com.project.paradoxplatformer.model.effect.managers.ChainOfEffectsBuilder;
import com.project.paradoxplatformer.model.trigger.Button;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the ChainOfEffects class, ensuring its functionality in
 * managing and applying a sequence of effects.
 */
class ChainOfEffectsTest {

    private NoOpEffect noOpEffect;
    private Button targetObject;

    /**
     * Initializes a NoOpEffect and a Button with default positions and dimensions
     * before each test.
     */
    @BeforeEach
    void setUp() {
        noOpEffect = new NoOpEffect();
        targetObject = new Button(0, new Coord2D(0, 0), new Dimension(0, 0));
    }

    /**
     * Tests that effects are applied sequentially and verifies that the application
     * of the NoOpEffect completes successfully.
     */
    @Test
    void testApplyEffectsSequentially() {
        final ChainOfEffects chain = ChainOfEffectsBuilder.builder()
                .addEffect(noOpEffect)
                .build();

        final CompletableFuture<Void> result = chain.applyToTarget(Optional.of(targetObject));
        result.join(); // Wait for async operation to complete

        // Verify that NoOpEffect was applied (it does nothing, but we can ensure it
        // didn't crash)
        assertTrue(result.isDone(), "Effect application did not complete successfully.");
    }

    /**
     * Tests that the builder correctly adds the NoOpEffect to the ChainOfEffects.
     */
    @Test
    void testBuilder() {
        final ChainOfEffects chain = ChainOfEffectsBuilder.builder()
                .addEffect(noOpEffect)
                .build();

        assertEquals(1, chain.getEffects().size(), "Builder did not correctly add the NoOpEffect.");
    }

    /**
     * Tests the static create method of ChainOfEffects to ensure it correctly
     * constructs a ChainOfEffects instance with the provided effects.
     */
    @Test
    void testCreate() {
        final ChainOfEffects chain = ChainOfEffects.create(List.of(() -> noOpEffect));

        assertEquals(1, chain.getEffects().size(), "Create method did not correctly construct ChainOfEffects.");
    }

    /**
     * Tests that the getEffects() method correctly retrieves the list of effects
     * contained in the ChainOfEffects.
     */
    @Test
    void testGetEffects() {
        final ChainOfEffects chain = ChainOfEffectsBuilder.builder()
                .addEffect(noOpEffect)
                .build();

        final List<Effect> effects = chain.getEffects();

        assertEquals(1, effects.size(), "Chain did not contain the correct number of effects.");
        assertEquals(noOpEffect, effects.get(0), "The NoOpEffect was not correctly added to the chain.");
    }
}
