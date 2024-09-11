package com.project.paradoxplatformer.utils.effect;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;
import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.Wall;
import com.project.paradoxplatformer.model.player.PlayerModel;

import com.project.paradoxplatformer.utils.effect.api.Effect;
import com.project.paradoxplatformer.utils.effect.api.RecreateableEffect;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import static com.project.paradoxplatformer.utils.OptionalUtils.peek;

public class EffectFactoryImpl implements EffectsFactory {

    @Override
    public Effect collectingEffect() {
        return new AbstractRecreatableEffect() {

            private Optional<PlayerModel> player = Optional.empty();

            @Override
            protected CompletableFuture<Void> applyToTarget(Optional<? extends CollidableGameObject> target) {
                return target.map(gameObject -> {
                    System.out.println("Target → " + target.get());
                    if (gameObject instanceof PlayerModel pl) {
                        this.player = Optional.of(pl);
                    }
                    return applyToGameObject(gameObject);
                }).orElseGet(Effect::empty);
            }

            @Override
            protected CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject) {
                return CompletableFuture.runAsync(() -> {
                    Optional.of(gameObject)
                            .filter(CollectableGameObject.class::isInstance)
                            .filter(g -> this.player.isPresent())
                            .map(CollectableGameObject.class::cast)
                            .map(peek(c -> System.out.println(c.getClass().getSimpleName() + " collected")))
                            .ifPresent(this.player.get()::collectItem);
                });
            }

            @Override
            public RecreateableEffect recreate() {
                return this;
            }

        };
    }

    @Override
    public Effect transitionEffect(final TrajectoryInfo traj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method'movingEffect'");
    }

    @Override
    public Effect stoppingEffect() {
        return new AbstractRecreatableEffect() {

            private Optional<PlayerModel> player = Optional.empty();

            @Override
            protected CompletableFuture<Void> applyToTarget(Optional<? extends CollidableGameObject> target) {
                return target.map(gameObject -> {
                    if (gameObject instanceof PlayerModel pl) {
                        this.player = Optional.of(pl);
                    }
                    return applyToGameObject(gameObject);
                }).orElseGet(Effect::empty);
            }

            @Override
            protected CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject) {
                return CompletableFuture.runAsync(() -> {
                    Optional.of(gameObject)
                            .filter(Wall.class::isInstance)
                            .filter(g -> this.player.isPresent())
                            .map(Wall.class::cast)
                            // .map(peek(c -> System.out.println(c.getClass().getSimpleName() + "
                            // stopping")))
                            // .map(peek(c -> System.out.println("Player pos → " +
                            // player.get().getPosition())))
                            .ifPresent(w -> {

                                this.player.get().counterForce();
                            });
                });
            }

            @Override
            public RecreateableEffect recreate() {
                return this;
            }

        };
    }

}
