package com.project.paradoxplatformer.utils.collision;

import org.junit.jupiter.api.Test;

import com.project.paradoxplatformer.model.effect.EffectHandler;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.Button;
import com.project.paradoxplatformer.model.trigger.Floor;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

/**
 * Unit test for CollisionManager handling collision detection and applying
 * effects.
 * Tests different game object collisions.
 */
public class CollisionTest {

    private static final double PLAYER_INITIAL_X = 0;
    private static final double PLAYER_INITIAL_Y = 0;
    private static final int PLAYER_DIMENSION = 50;
    private static final int OBJECT_DIMENSION = 50;
    private static final double INCORRECT_X = 200.;
    private static final double INCORRECT_Y = 400.;

    /**
     * Tests the collision detection between a player, a button, and a floor object,
     * and verifies that the correct objects are detected as colliding or not.
     */
    @Test
    public void testCollisionDetectionAndEffectApplication() {
        EffectHandler effectHandler = new EffectHandler();
        CollisionManager collisionManager = new CollisionManager(effectHandler);

        PlayerModel player = new PlayerModel(new Coord2D(PLAYER_INITIAL_X, PLAYER_INITIAL_Y),
                new Dimension(PLAYER_DIMENSION, PLAYER_DIMENSION));
        Button button = new Button(new Coord2D(PLAYER_INITIAL_X, PLAYER_INITIAL_Y),
                new Dimension(OBJECT_DIMENSION, OBJECT_DIMENSION));
        Floor floor = new Floor(new Coord2D(INCORRECT_X, INCORRECT_Y),
                new Dimension(OBJECT_DIMENSION, OBJECT_DIMENSION));

        // Simulate a list of CollidableGameObjects
        List<? extends CollidableGameObject> collidables = List.of(player, button, floor);

        // Simulate collision detection
        collisionManager.handleCollisions(collidables, player);

        assertTrue(CollisionDetector.hasCollision(player, List.of(button)), "Player should collide with the button.");
        assertFalse(CollisionDetector.hasCollision(player, List.of(floor)),
                "Player should not collide with the floor.");
    }
}
