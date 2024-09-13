package com.project.paradoxplatformer.utils.effect;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.ResourcesFinder;
import com.project.paradoxplatformer.utils.effect.api.RecreateableEffect;
import com.project.paradoxplatformer.utils.sound.SoundLoader;
import com.project.paradoxplatformer.utils.sound.SoundType;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Represents an effect that plays a sound. The sound is played only once
 * unless explicitly reset.
 */
public class SoundEffect extends AbstractRecreatableEffect {
    private final SoundType soundType;
    private final SoundLoader soundLoader;
    private boolean hasPlayed = false;

    /**
     * Creates a new SoundEffect.
     *
     * @param soundFilePath the path to the sound file to play
     */
    public SoundEffect(SoundType soundType) {
        this.soundType = soundType;
        this.soundLoader = new SoundLoader();
    }

    @Override
    protected CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject) {
        if (hasPlayed) {
            return CompletableFuture.completedFuture(null);
        }
        hasPlayed = true; // Set flag to true once sound starts playing
        try {
            return soundLoader.playSound(ResourcesFinder.getURL(soundType.getSoundName()));
        } catch (InvalidResourceException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(null);
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
        return new SoundEffect(soundType);
    }

    @Override
    protected CompletableFuture<Void> applyToSelf(Optional<? extends CollidableGameObject> self) {
        return CompletableFuture.completedFuture(null);
    }
}
