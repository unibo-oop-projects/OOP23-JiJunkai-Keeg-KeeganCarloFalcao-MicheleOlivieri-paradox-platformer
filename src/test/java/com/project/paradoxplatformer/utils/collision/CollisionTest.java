package com.project.paradoxplatformer.utils.collision;

import org.junit.jupiter.api.Test;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.api.Button;
import com.project.paradoxplatformer.model.trigger.api.Warp;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.effect.EffectHandler;
import com.project.paradoxplatformer.utils.effect.api.Effect;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class CollisionTest {
    @Test
    public void testCollisionDetectionAndEffectApplication() {
        EffectHandler effectHandler = new EffectHandler();
        CollisionManager collisionManager = new CollisionManager(effectHandler);

        PlayerModel player = new PlayerModel(new Coord2D(0, 0), new Dimension(50, 50), null);
        Button button = new Button();
        Warp warp = new Warp(100, 200);

        // Register effects
        effectHandler.addCollisionEffects(CollisionType.TRIGGER, button, List.of(() -> new Effect() {
            @Override
            public CompletableFuture<Void> apply(Optional<? extends CollidableGameObject> target) {
                System.out.println("Button effect applied");
                return CompletableFuture.completedFuture(null);
            }
        }));

        effectHandler.addCollisionEffects(CollisionType.TRIGGER, warp, List.of(() -> new Effect() {
            @Override
            public CompletableFuture<Void> apply(Optional<? extends CollidableGameObject> target) {
                if (target.isPresent() && target.get() instanceof PlayerModel) {
                    ((PlayerModel) target.get()).setPosition(new Coord2D(100., 200.));
                }
                return CompletableFuture.completedFuture(null);
            }
        }));

        // Simulate a list of CollidableGameObjects
        List<? extends CollidableGameObject> collidables = List.of(player, button, warp);

        // Simulate collision detection
        collisionManager.detectCollisions(collidables, player);

        assertTrue(player.getPosition().x() == 100. && player.getPosition().y() == 200.);
        assertFalse(player.getPosition().x() == 200. && player.getPosition().y() == 400.);
    }
}