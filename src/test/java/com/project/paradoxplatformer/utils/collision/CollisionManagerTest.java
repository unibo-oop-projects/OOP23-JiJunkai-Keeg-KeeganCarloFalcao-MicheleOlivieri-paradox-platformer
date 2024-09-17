package com.project.paradoxplatformer.utils.collision;

import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.model.effect.api.EffectHandler;
import com.project.paradoxplatformer.model.effect.impl.EffectHandlerFactoryImpl;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.Button;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class CollisionManagerTest {

    private CollisionManager collisionManager;
    private EffectHandler testEffectHandler;

    @BeforeEach
    void setUp() {
        testEffectHandler = new EffectHandlerFactoryImpl().getEffectHandlerForLevel(Level.EMPTY_LEVEL);
        collisionManager = new CollisionManager(testEffectHandler);
    }

    @Test
    void testHandleCollisionsWithCollisions() {
        // Setup test objects
        CollidableGameObject player = new PlayerModel();
        CollidableGameObject object1 = new Button(0, new Coord2D(0, 0), new Dimension(0, 0));
        object1.setPosition(new Coord2D(0, 0)); // Position overlaps with player
        player.setPosition(new Coord2D(0, 0)); // Position matches object1

        Set<CollidableGameObject> collidableObjects = new HashSet<>();
        collidableObjects.add(object1);

        // Test the method
        collisionManager.handleCollisions(collidableObjects, player);

    }

    @Test
    void testHandleCollisionsWithoutCollisions() {
        // Setup test objects
        CollidableGameObject player = new PlayerModel();
        CollidableGameObject object1 = new Button(1, new Coord2D(0, 0), new Dimension(0, 0));
        object1.setPosition(new Coord2D(10, 10)); // Position does not overlap with player
        player.setPosition(new Coord2D(0, 0)); // Position different from object1

        Set<CollidableGameObject> collidableObjects = new HashSet<>();
        collidableObjects.add(object1);

        // Test the method
        collisionManager.handleCollisions(collidableObjects, player);

    }

}
