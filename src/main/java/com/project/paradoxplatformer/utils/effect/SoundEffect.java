package com.project.paradoxplatformer.utils.effect;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.effect.api.RecreateableEffect;
import com.project.paradoxplatformer.utils.sound.SoundLoader;
import java.util.concurrent.CompletableFuture;

/**
 * Represents an effect that plays a sound. The sound is played only once
 * unless explicitly reset.
 */
public class SoundEffect extends AbstractRecreatableEffect {
    private final String soundFilePath;
    private final SoundLoader soundLoader;
    private boolean hasPlayed = false; // Flag to track if the sound has already played

    /**
     * Creates a new SoundEffect.
     *
     * @param soundFilePath the path to the sound file to play
     */
    public SoundEffect(String soundFilePath) {
        this.soundFilePath = soundFilePath;
        this.soundLoader = new SoundLoader();
    }

    @Override
    protected CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject) {
        if (hasPlayed) {
            return CompletableFuture.completedFuture(null); // Skip if already played
        }
        hasPlayed = true; // Set flag to true once sound starts playing
        return soundLoader.playSound(soundFilePath);
    }

    /**
     * Resets the sound effect so that the sound can be played again.
     */
    public void reset() {
        hasPlayed = false;
    }

    @Override
    public RecreateableEffect recreate() {
        System.out.println("Sound Effect gets recreated");
        return new SoundEffect(soundFilePath);
    }
}
