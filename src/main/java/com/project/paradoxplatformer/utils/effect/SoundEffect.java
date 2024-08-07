package com.project.paradoxplatformer.utils.effect;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.api.Collidable;
import com.project.paradoxplatformer.utils.effect.api.Effect;

public class SoundEffect implements Effect {
    private final String sound;

    public SoundEffect(String sound) {
        this.sound = sound;
    }

    @Override
    public CompletableFuture<Void> apply(Optional<? extends Collidable> target) {
        return CompletableFuture.runAsync(() -> {
            System.out.println("Playing sound effect: " + sound);
        });
    }
}