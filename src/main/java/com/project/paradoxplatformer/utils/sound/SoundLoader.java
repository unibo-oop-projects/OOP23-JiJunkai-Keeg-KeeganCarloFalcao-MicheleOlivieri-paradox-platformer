package com.project.paradoxplatformer.utils.sound;

import javax.sound.sampled.*;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.ResourcesFinder;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class SoundLoader {

    // Load and play sound from the provided file path
    public CompletableFuture<Void> playSound(String soundFilePath) {
        return CompletableFuture.runAsync(() -> {
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(ResourcesFinder.getURL(soundFilePath))) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();

                // Wait for the clip to finish playing
                clip.drain();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InvalidResourceException e) {
                throw new RuntimeException("Error on sounds ", e);
            }
        });
    }
}
