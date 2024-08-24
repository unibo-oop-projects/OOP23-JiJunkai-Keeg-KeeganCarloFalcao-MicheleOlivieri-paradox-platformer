package com.project.paradoxplatformer.utils.effect;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.effect.api.Effect;
import com.project.paradoxplatformer.utils.sound.SoundLoader;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class SoundEffect implements Effect {
    private final String soundFilePath;
    private final SoundLoader soundLoader;
    private boolean hasPlayed = false; // Flag to track if the sound has already played

    public SoundEffect(String soundFilePath) {
        this.soundFilePath = soundFilePath;
        this.soundLoader = new SoundLoader();
    }

    @Override
    public CompletableFuture<Void> apply(Optional<? extends CollidableGameObject> target) {
        if (hasPlayed) {
            return CompletableFuture.completedFuture(null); // Skip if already played
        }
        hasPlayed = true; // Set flag to true once sound starts playing
        return soundLoader.playSound(soundFilePath);
    }
}
