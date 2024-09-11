package com.project.paradoxplatformer.utils.effect;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.effect.api.Level;

public class TriggerEffect extends AbstractOneTimeEffect {

    private final Level level;

    public TriggerEffect(Level level) {
        this.level = level;
    }

    @Override
    protected CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject) {
        return CompletableFuture.completedFuture(null);
    }

}
