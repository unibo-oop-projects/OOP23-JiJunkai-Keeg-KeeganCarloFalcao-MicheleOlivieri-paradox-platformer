package com.project.paradoxplatformer.utils.collision.effects;

import com.project.paradoxplatformer.utils.collision.api.Effect;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class SoundEffect implements Effect {
    private final String sound;

    public SoundEffect(String sound) {
        this.sound = sound;
    }

    @Override
    public CompletableFuture<Void> apply(Optional<Object> target) {
        return CompletableFuture.runAsync(() -> {
            System.out.println("Playing sound effect: " + sound);
        });
    }
}