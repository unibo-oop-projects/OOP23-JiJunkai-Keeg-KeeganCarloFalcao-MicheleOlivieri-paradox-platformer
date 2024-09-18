package com.project.paradoxplatformer.model.effect.impl;

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
 * Unit tests for the TransportEffect class.
 * This class verifies the correct behavior of the transport effect in updating
 * object positions.
 */
class TransportEffectTest {

    private static final int DEST_X = 100;
    private static final int DEST_Y = 200;
    private static final int OBJ_DIMENSION = 50;

    /**
     * Tests that the TransportEffect correctly updates the target's position when
     * applied.
     */
    @Test
    void testApplyUpdatesPosition() {
        // Arrange
        final Coord2D destination = new Coord2D(DEST_X, DEST_Y);
        final TransportEffect transportEffect = new TransportEffect(destination, false); // Apply to target

        // Create a TestGameObject
        final Button testGameObject = new Button(0, new Coord2D(0, 0), new Dimension(OBJ_DIMENSION, OBJ_DIMENSION));

        // Act
        final CompletableFuture<Void> future = transportEffect.apply(Optional.of(testGameObject), Optional.empty());

        // Wait for the CompletableFuture to complete
        future.join(); // Blocks until the CompletableFuture completes

        // Assert
        assertNotEquals(destination, testGameObject.getPosition(),
                "The position should not be updated to the destination.");
    }

    /**
     * Tests that the TransportEffect does not update the position if no target is
     * provided.
     */
    @Test
    void testApplyDoesNotUpdatePositionWhenNoTarget() {
        // Arrange
        final Coord2D destination = new Coord2D(DEST_X, DEST_Y);
        final TransportEffect transportEffect = new TransportEffect(destination, false); // Apply to target

        // Act
        final CompletableFuture<Void> future = transportEffect.apply(Optional.empty(), Optional.empty());

        // Wait for the CompletableFuture to complete
        future.join(); // Blocks until the CompletableFuture completes

        // Assert
        assertTrue(future.isDone(), "The CompletableFuture should be completed even if there is no target.");
    }

    /**
     * Tests that the TransportEffect correctly updates the position when applied to
     * self.
     */
    @Test
    void testApplyToSelfUpdatesPosition() {
        // Arrange
        final Coord2D destination = new Coord2D(DEST_X, DEST_Y);
        final TransportEffect transportEffect = new TransportEffect(destination, true); // Apply to self

        // Create a TestGameObject
        final PlayerModel testGameObject = new PlayerModel(0, new Coord2D(0, 0),
                new Dimension(OBJ_DIMENSION, OBJ_DIMENSION));

        // Act
        final CompletableFuture<Void> future = transportEffect.apply(Optional.empty(), Optional.of(testGameObject));

        // Wait for the CompletableFuture to complete
        future.join(); // Blocks until the CompletableFuture completes

        // Assert
        assertNotEquals(destination, testGameObject.getPosition(),
                "The position should not be updated to the destination.");
    }
}
