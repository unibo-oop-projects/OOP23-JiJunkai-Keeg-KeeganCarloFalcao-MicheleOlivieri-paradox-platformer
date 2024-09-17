package com.project.paradoxplatformer.model.effect.impl;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.effect.abstracts.AbstractRecreatableEffect;
import com.project.paradoxplatformer.model.effect.api.RecreateableEffect;
import com.project.paradoxplatformer.model.trigger.Trigger;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

import static com.project.paradoxplatformer.utils.OptionalUtils.peek;

public final class TriggerEffect extends AbstractRecreatableEffect{

    @Override
    public RecreateableEffect recreate() {
        return this;
    }

    @Override
    protected CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject) {
        return CompletableFuture.runAsync(() -> {
            Optional.of(gameObject)
                    .filter(Trigger.class::isInstance)
                    .map(Trigger.class::cast)
                    .map(peek(c -> System.out.println(c.getClass().getSimpleName() + " activated")))
                    .ifPresent(Trigger::activate);

        });
    }
    
}
