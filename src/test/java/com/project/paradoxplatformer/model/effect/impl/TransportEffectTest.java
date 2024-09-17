package com.project.paradoxplatformer.model.effect.impl;

import com.project.paradoxplatformer.model.trigger.Button;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class TransportEffectTest {

    private TransportEffect transportEffectToTarget;
    private TransportEffect transportEffectToSelf;
    private Button button;

    @BeforeEach
    void setUp() {
        Coord2D destination = new Coord2D(300, 300);
        transportEffectToTarget = new TransportEffect(destination, true);
        transportEffectToSelf = new TransportEffect(destination, false);
        button = new Button(0, new Coord2D(100, 200),
                new com.project.paradoxplatformer.utils.geometries.Dimension(50, 50));
    }

    @Test
    void testApplyToTarget_TransportsTarget() {
        // Test applying the effect to the target (Button)
        Optional<Button> target = Optional.of(button);
        CompletableFuture<Void> future = transportEffectToTarget.applyToTarget(target);

        // Wait for the future to complete
        future.join();

        // Assert that the button was transported to the new destination
        assertEquals(new Coord2D(300, 300), button.getPosition(),
                "The button should be transported to the destination.");
    }

    @Test
    void testApplyToSelf_TransportsSelf() {
        // Test applying the effect to self (Button)
        Optional<Button> self = Optional.of(button);
        CompletableFuture<Void> future = transportEffectToSelf.applyToSelf(self);

        // Wait for the future to complete
        future.join();

        // Assert that the button was transported to the new destination
        assertEquals(new Coord2D(300, 300), button.getPosition(),
                "The button should be transported to the destination.");
    }

    @Test
    void testApplyToTarget_NoTargetPresent() {
        // Test applying the effect when no target is present
        Optional<CollidableGameObject> noTarget = Optional.empty();
        CompletableFuture<Void> future = transportEffectToTarget.applyToTarget(noTarget);

        // Assert that the CompletableFuture is already completed since no target was
        // present
        assertTrue(future.isDone(), "The future should be completed immediately when no target is present.");
    }

    @Test
    void testRecreate_CreatesNewTransportEffect() {
        // Test recreating the effect
        TransportEffect recreatedEffect = (TransportEffect) transportEffectToTarget.recreate();

        // Assert that the recreated effect is not the same instance
        assertNotNull(recreatedEffect, "The recreated effect should not be null.");
        assertNotSame(transportEffectToTarget, recreatedEffect, "The recreated effect should be a new instance.");

    }
}
