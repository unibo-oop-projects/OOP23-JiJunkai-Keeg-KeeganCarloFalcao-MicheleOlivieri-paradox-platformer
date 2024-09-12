package com.project.paradoxplatformer.utils.sound;

import javax.sound.sampled.*;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class SoundLoader {

    // Load and play sound from the provided URL
    public CompletableFuture<Void> playSound(URL soundUrl) {
        return CompletableFuture.runAsync(() -> {
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl)) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();

                // Wait for the clip to finish playing
                clip.drain();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                throw new RuntimeException("Error on sounds ", e);
            }
        });
    }
}
