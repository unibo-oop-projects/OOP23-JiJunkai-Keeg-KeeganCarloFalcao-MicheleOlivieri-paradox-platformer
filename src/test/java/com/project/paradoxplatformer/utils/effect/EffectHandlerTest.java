package com.project.paradoxplatformer.utils.effect;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.api.Button;
import com.project.paradoxplatformer.utils.collision.CollisionManager;
import com.project.paradoxplatformer.utils.collision.CollisionDetector;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;

public class EffectHandlerTest {

    private EffectHandler effectHandler;
    @SuppressWarnings("unused")
    private CollisionManager collisionManager;
    private CollidableGameObject mockPlayer;
    private CollidableGameObject mockObject;

    @BeforeEach
    public void setUp() {
        effectHandler = new EffectHandlerFactoryImpl().defaultEffectHandler();
        collisionManager = new CollisionManager(effectHandler);

        // Mock CollidableGameObjects
        mockPlayer = new PlayerModel();
        mockObject = new Button();

        // Simulate a collision detection
        CollisionDetector.setCollisionResult(true);
    }

    @Test
    public void testNoOpEffectAppliesAsync() {
        // Define an asynchronous effect
        NoOpEffect noOpEffect = new NoOpEffect();

        // Register the effect
        effectHandler.addCollisionEffectsForType(CollisionType.OBSTACLE, () -> noOpEffect);

        // Trigger the collision detection and apply effects
        CompletableFuture<Void> future = effectHandler.applyEffects(mockPlayer, mockObject);

        // Wait for the effect to complete
        future.join(); // Blocks until all effects are completed

        // Optionally, use additional validation to confirm effects were applied
    }

}
