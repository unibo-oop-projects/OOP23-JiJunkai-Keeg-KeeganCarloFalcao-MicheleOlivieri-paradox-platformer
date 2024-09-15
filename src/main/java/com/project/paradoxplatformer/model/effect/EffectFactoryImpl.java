package com.project.paradoxplatformer.model.effect;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.controller.event.EventManager;
import com.project.paradoxplatformer.controller.event.GameEventType;
import com.project.paradoxplatformer.model.effect.abstracts.AbstractRecreatableEffect;
import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.model.effect.api.RecreateableEffect;
import com.project.paradoxplatformer.model.entity.CollectableGameObject;
import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.Wall;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.geometries.modifiers.Direction;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

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
            protected CompletableFuture<Void> applyToSelf(Optional<? extends CollidableGameObject> self) {
                return super.applyToSelf(self).thenAccept(obj -> EventManager.getInstance()
                        .publish(GameEventType.REMOVE_OBJECT, PageIdentifier.GAME, self));
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

            private static final double TOLERANCE = 1.d;

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
                            .ifPresent(w -> {
                                if (player.get().direction() == Direction.LEFT) {
                                    this.player.get().setDisplacement(
                                            w.getPosition().x() + w.getDimension().width() + TOLERANCE);
                                } else if (player.get().direction() == Direction.RIGHT) {
                                    this.player.get().setDisplacement(
                                            w.getPosition().x() - this.player.get().getDimension().width() - TOLERANCE);
                                }
                                System.out.println(this.player.get().toString());

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
