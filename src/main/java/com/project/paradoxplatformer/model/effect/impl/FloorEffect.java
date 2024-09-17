package com.project.paradoxplatformer.model.effect.impl;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.effect.abstracts.AbstractRecreatableEffect;
import com.project.paradoxplatformer.model.effect.api.RecreateableEffect;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

public class FloorEffect extends AbstractRecreatableEffect {

    @Override
    public RecreateableEffect recreate() {
        return this;
    }

    @Override
    protected CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject) {
        return CompletableFuture.runAsync(() -> {
            if (gameObject instanceof ControllableObject controllableObject) {
                controllableObject.stopFall();
            }
        });
    }

}
