package com.project.paradoxplatformer.model.player;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.project.paradoxplatformer.controller.gameloop.GameLoop;
import com.project.paradoxplatformer.model.entity.CollectableGameObject;
import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.model.obstacles.Coin;
import com.project.paradoxplatformer.utils.collision.CollisionManager;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.effect.EffectFactoryImpl;
import com.project.paradoxplatformer.utils.effect.EffectHandler;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public class SimpleInventoryTest {
    private static final double COIN_POSITION_X_2 = 50;
    private static final double COIN_POSITION_X_1 = 20;

    @Test
    void simpleCollectingItem() {

        PlayerModel player = new PlayerModel();
        CollectableGameObject coin = new Coin(Coord2D.origin(), Dimension.dot());
        
        player.collectItem(coin);

        System.out.println(player.getInventoryData());
        assertTrue(player.getInventoryData().get(Coin.class.getSimpleName()) == 1L);

        //Collects another coin
        CollectableGameObject coin2 = new Coin(Coord2D.origin(), Dimension.dot());
        player.collectItem(coin2);

        assertTrue(player.getInventoryData().get(Coin.class.getSimpleName()) == 2L);

        //Collects same coin
        player.collectItem(coin2);
        assertTrue(player.getInventoryData().get(Coin.class.getSimpleName()) == 2L);
        
    }

    @Test
    void collectingWithCollision() {

        final PlayerModel player = new PlayerModel();
        final CollectableGameObject coin = new Coin(new Coord2D(COIN_POSITION_X_1, 0), new Dimension(20, 20));
        final CollectableGameObject coin2 = new Coin(new Coord2D(COIN_POSITION_X_2, 0), new Dimension(20, 20));

        final EffectHandler effectHandler = new EffectHandler();
        effectHandler.addCollisionEffectsForType(CollisionType.COLLECTING, new EffectFactoryImpl()::collectingEffect);
        final CollisionManager collisionManager = new CollisionManager(effectHandler);

        final List<? extends CollidableGameObject> collidables = List.of(player, coin, coin2);

        //Main gameloop 
        final GameLoop loop = dt -> {
            player.moveRight();
            player.updateState(dt);
            collisionManager.handleCollisions(collidables, player);
        };

        //Simulates a gameloop manager
        simulateLoop(loop, 20);

        //Prevents accessing to unexistent data
        assertFalse(player.getInventoryData().isEmpty());

        //Collects one coin
        assertTrue(player.getInventoryData().get(Coin.class.getSimpleName()) == 1L);

        simulateLoop(loop, 40);
        //Collects two different coins
        assertTrue(player.getInventoryData().get(Coin.class.getSimpleName()) == 2L);
        
    }

    private void simulateLoop(final GameLoop loop, int steps) {
        IntStream.range(0, steps)
            .boxed()
            .map(i -> 15)
            .forEach(loop::loop);
    }
}
