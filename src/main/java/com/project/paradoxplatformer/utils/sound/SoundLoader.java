package com.project.paradoxplatformer.utils.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class SoundLoader {

    // Load and play sound from the provided file path
    public CompletableFuture<Void> playSound(String soundFilePath) {
        return CompletableFuture.runAsync(() -> {
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFilePath))) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();

                // Wait for the clip to finish playing
                clip.drain();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        });
    }
}
