package com.project.paradoxplatformer.utils.effect.impl;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.effect.AbstractRecreatableEffect;
import com.project.paradoxplatformer.utils.effect.api.RecreateableEffect;

public class SpringEffect extends AbstractRecreatableEffect {

    public SpringEffect() {
    }

    @Override
    public RecreateableEffect recreate() {
        return this;
    }

    @Override
    protected CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject) {
        return CompletableFuture.runAsync(() -> {
            if (gameObject instanceof ControllableObject) {
                ((ControllableObject) gameObject).jump();
            }
        });
    }
}
