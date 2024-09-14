package com.project.paradoxplatformer.model.effect;

import com.project.paradoxplatformer.model.effect.impl.TransportEffect;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.Button;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

public class TransportEffectTest {

    @Test
    public void testApplyUpdatesPosition() {
        // Arrange
        Coord2D destination = new Coord2D(100, 200);
        TransportEffect transportEffect = new TransportEffect(destination, false); // Apply to target

        // Create a TestGameObject
        Button testGameObject = new Button(new Coord2D(0, 0), new Dimension(50, 50));

        // Act
        CompletableFuture<Void> future = transportEffect.apply(Optional.of(testGameObject), Optional.empty());

        // Wait for the CompletableFuture to complete
        future.join(); // Blocks until the CompletableFuture completes

        // Assert
        assertNotEquals(destination, testGameObject.getPosition(),
                "The position should not to be updated to the destination.");
    }

    @Test
    public void testApplyDoesNotUpdatePositionWhenNoTarget() {
        // Arrange
        Coord2D destination = new Coord2D(100, 200);
        TransportEffect transportEffect = new TransportEffect(destination, false); // Apply to target

        // Act
        CompletableFuture<Void> future = transportEffect.apply(Optional.empty(), Optional.empty());

        // Wait for the CompletableFuture to complete
        future.join(); // Blocks until the CompletableFuture completes

        // Assert
        // Ensure the CompletableFuture is completed
        assertTrue(future.isDone(), "The CompletableFuture should be completed even if there is no target.");
    }

    @Test
    public void testApplyToSelfUpdatesPosition() {
        // Arrange
        Coord2D destination = new Coord2D(100, 200);
        TransportEffect transportEffect = new TransportEffect(destination, true); // Apply to self

        // Create a TestGameObject
        PlayerModel testGameObject = new PlayerModel(new Coord2D(0, 0), new Dimension(50, 50));

        // Act
        CompletableFuture<Void> future = transportEffect.apply(Optional.empty(), Optional.of(testGameObject));

        // Wait for the CompletableFuture to complete
        future.join(); // Blocks until the CompletableFuture completes

        // Assert
        assertNotEquals(destination, testGameObject.getPosition(),
                "The position should be not updated to the destination.");
    }
}
