package com.project.paradoxplatformer.utils.collision;

import com.project.paradoxplatformer.model.effect.EffectHandler;
import com.project.paradoxplatformer.model.effect.EffectHandlerFactoryImpl;
import com.project.paradoxplatformer.model.effect.api.EffectHandlerFactory;
import com.project.paradoxplatformer.model.trigger.api.Button;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class CollisionManagerTest {

    private CollisionManager collisionManager;
    private EffectHandler testEffectHandler;

    @BeforeEach
    void setUp() {
        testEffectHandler = new EffectHandlerFactoryImpl().defaultEffectHandler();
        collisionManager = new CollisionManager(testEffectHandler);
    }

    @Test
    void testHandleCollisions_WithCollisions() {
        // Setup test objects
        CollidableGameObject player = new Button();
        CollidableGameObject object1 = new Button();
        object1.setPosition(new Coord2D(0, 0)); // Position overlaps with player
        player.setPosition(new Coord2D(0, 0)); // Position matches object1

        Set<CollidableGameObject> collidableObjects = new HashSet<>();
        collidableObjects.add(object1);

        // Test the method
        collisionManager.handleCollisions(collidableObjects, player);

    }

    @Test
    void testHandleCollisions_WithoutCollisions() {
        // Setup test objects
        CollidableGameObject player = new Button();
        CollidableGameObject object1 = new Button();
        object1.setPosition(new Coord2D(10, 10)); // Position does not overlap with player
        player.setPosition(new Coord2D(0, 0)); // Position different from object1

        Set<CollidableGameObject> collidableObjects = new HashSet<>();
        collidableObjects.add(object1);

        // Test the method
        collisionManager.handleCollisions(collidableObjects, player);

    }

}
