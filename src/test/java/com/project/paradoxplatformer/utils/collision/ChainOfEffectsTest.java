package com.project.paradoxplatformer.utils.collision;

import com.project.paradoxplatformer.model.effect.ChainOfEffects;
import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.model.effect.impl.NoOpEffect;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChainOfEffectsTest {

    private NoOpEffect noOpEffect;
    private TestCollidableGameObject targetObject;

    @BeforeEach
    void setUp() {
        noOpEffect = new NoOpEffect();
        targetObject = new TestCollidableGameObject("Target");
    }

    @Test
    void testApplyEffectsSequentially() {
        ChainOfEffects chain = ChainOfEffects.builder()
                .addEffect(noOpEffect)
                .build();

        CompletableFuture<Void> result = chain.applyEffectsSequentially(Optional.of(targetObject));
        result.join(); // Wait for async operation to complete

        // Verify that NoOpEffect was applied (it does nothing, but we can ensure it
        // didn't crash)
        assertTrue(result.isDone(), "Effect application did not complete successfully.");
    }

    @Test
    void testBuilder() {
        ChainOfEffects chain = ChainOfEffects.builder()
                .addEffect(noOpEffect)
                .build();

        assertEquals(1, chain.getEffects().size(), "Builder did not correctly add the NoOpEffect.");
    }

    @Test
    void testCreate() {
        ChainOfEffects chain = ChainOfEffects.create(List.of(() -> noOpEffect));

        assertEquals(1, chain.getEffects().size(), "Create method did not correctly construct ChainOfEffects.");
    }

    @Test
    void testGetEffects() {
        ChainOfEffects chain = ChainOfEffects.builder()
                .addEffect(noOpEffect)
                .build();

        List<Effect> effects = chain.getEffects();

        assertEquals(1, effects.size(), "Chain did not contain the correct number of effects.");
        assertEquals(noOpEffect, effects.get(0), "The NoOpEffect was not correctly added to the chain.");
    }

    // Inner class to simulate a CollidableGameObject for testing purposes
    static class TestCollidableGameObject implements CollidableGameObject {
        private final String name;

        public TestCollidableGameObject(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public Coord2D getPosition() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
        }

        @Override
        public Dimension getDimension() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getDimension'");
        }

        @Override
        public void setPosition(Coord2D position) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setPosition'");
        }

        @Override
        public void setDimension(Dimension dimension) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setDimension'");
        }

        @Override
        public CollisionType getCollisionType() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getCollisionType'");
        }
    }
}
