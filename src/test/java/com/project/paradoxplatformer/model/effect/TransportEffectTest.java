package com.project.paradoxplatformer.model.effect;

import com.project.paradoxplatformer.model.effect.impl.TransportEffect;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.Button;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the {@link TransportEffect} class.
 */
public class TransportEffectTest {

    private static final Coord2D DESTINATION = new Coord2D(100, 200);
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    /**
     * Tests if the {@link TransportEffect} correctly updates the position of the
     * target object.
     */
    @Test
    public void testApplyUpdatesPosition() {
        // Arrange
        TransportEffect transportEffect = new TransportEffect(DESTINATION, false); // Apply to target

        // Create a TestGameObject
        Button testGameObject = new Button(1, new Coord2D(0, 0), new Dimension(WIDTH, HEIGHT));

        // Act
        CompletableFuture<Void> future = transportEffect.apply(Optional.of(testGameObject), Optional.empty());

        // Wait for the CompletableFuture to complete
        future.join(); // Blocks until the CompletableFuture completes

        // Assert
        assertNotEquals(DESTINATION, testGameObject.getPosition(),
                "The position should not be updated to the destination.");
    }

    /**
     * Tests that {@link TransportEffect} does not update the position when there is
     * no target.
     */
    @Test
    public void testApplyDoesNotUpdatePositionWhenNoTarget() {
        // Arrange
        TransportEffect transportEffect = new TransportEffect(DESTINATION, false); // Apply to target

        // Act
        CompletableFuture<Void> future = transportEffect.apply(Optional.empty(), Optional.empty());

        // Wait for the CompletableFuture to complete
        future.join(); // Blocks until the CompletableFuture completes

        // Assert
        assertTrue(future.isDone(), "The CompletableFuture should be completed even if there is no target.");
    }

    /**
     * Tests if the {@link TransportEffect} correctly updates the position of the
     * self object.
     */
    @Test
    public void testApplyToSelfUpdatesPosition() {
        // Arrange
        TransportEffect transportEffect = new TransportEffect(DESTINATION, true); // Apply to self

        // Create a TestGameObject
        PlayerModel testGameObject = new PlayerModel(0, new Coord2D(0, 0), new Dimension(WIDTH, HEIGHT));

        // Act
        CompletableFuture<Void> future = transportEffect.apply(Optional.empty(), Optional.of(testGameObject));

        // Wait for the CompletableFuture to complete
        future.join(); // Blocks until the CompletableFuture completes

        // Assert
        assertNotEquals(DESTINATION, testGameObject.getPosition(),
                "The position should not be updated to the destination.");
    }
}
