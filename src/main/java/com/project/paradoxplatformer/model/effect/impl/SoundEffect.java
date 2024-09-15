package com.project.paradoxplatformer.model.effect.impl;

import com.project.paradoxplatformer.model.effect.abstracts.AbstractRecreatableEffect;
import com.project.paradoxplatformer.model.effect.api.RecreateableEffect;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.ResourcesFinder;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.sound.SoundLoader;
import com.project.paradoxplatformer.utils.sound.SoundType;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Represents an effect that plays a sound. The sound is played only once
 * unless explicitly reset.
 */
public final class SoundEffect extends AbstractRecreatableEffect {

    private final SoundType soundType; // The type of sound to be played
    private final SoundLoader soundLoader; // Loader to handle sound playback
    private boolean hasPlayed = false; // Flag to track if the sound has already been played

    /**
     * Creates a new SoundEffect.
     *
     * @param soundType the type of sound to play
     */
    public SoundEffect(final SoundType soundType) {
        this.soundType = soundType;
        this.soundLoader = new SoundLoader();
    }

    /**
     * Applies the sound effect to the specified game object.
     * This method will play the sound if it hasn't been played yet.
     *
     * @param gameObject the game object to which the effect is applied
     * @return a CompletableFuture that completes when the sound has been played
     */
    @Override
    protected CompletableFuture<Void> applyToGameObject(final CollidableGameObject gameObject) {
        if (hasPlayed) {
            return CompletableFuture.completedFuture(null);
        }
        hasPlayed = true; // Set flag to true once sound starts playing
        try {
            return soundLoader.playSound(ResourcesFinder.getURL(soundType.getSoundName()));
        } catch (InvalidResourceException e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(null);
        }
    }

    /**
     * Resets the sound effect so that the sound can be played again.
     */
    public void reset() {
        hasPlayed = false;
    }

    /**
     * Creates a new instance of this SoundEffect, effectively recreating it.
     *
     * @return a new SoundEffect instance
     */
    @Override
    public RecreateableEffect recreate() {
        System.out.println("Sound Effect gets recreated");
        return new SoundEffect(soundType);
    }

    /**
     * Applies the sound effect to the current instance (self).
     * This method does nothing for SoundEffect as it is not intended to affect
     * self.
     *
     * @param self the current instance of CollidableGameObject (not used)
     * @return a CompletableFuture that completes immediately
     */
    @Override
    protected CompletableFuture<Void> applyToSelf(final Optional<? extends CollidableGameObject> self) {
        return CompletableFuture.completedFuture(null);
    }
}
