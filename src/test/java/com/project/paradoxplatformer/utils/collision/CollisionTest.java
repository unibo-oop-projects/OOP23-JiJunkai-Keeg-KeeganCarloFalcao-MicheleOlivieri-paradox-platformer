package com.project.paradoxplatformer.utils.collision;

import org.junit.jupiter.api.Test;

import com.project.paradoxplatformer.model.effect.EffectHandler;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.api.Button;
import com.project.paradoxplatformer.model.trigger.api.Floor;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class CollisionTest {
    @Test
    public void testCollisionDetectionAndEffectApplication() {
        EffectHandler effectHandler = new EffectHandler();
        CollisionManager collisionManager = new CollisionManager(effectHandler);

        PlayerModel player = new PlayerModel(new Coord2D(0, 0), new Dimension(50, 50));
        Button button = new Button();
        Floor floor = new Floor(new Coord2D(0, 0), new Dimension(0, 0));

        // Simulate a list of CollidableGameObjects
        List<? extends CollidableGameObject> collidables = List.of(player, button, floor);

        // Simulate collision detection
        collisionManager.handleCollisions(collidables, player);

        assertTrue(player.getPosition().x() == 100. && player.getPosition().y() == 200.);
        assertFalse(player.getPosition().x() == 200. && player.getPosition().y() == 400.);
    }
}