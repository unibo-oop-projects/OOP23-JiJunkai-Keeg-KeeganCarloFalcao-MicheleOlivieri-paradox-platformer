package com.project.paradoxplatformer.utils.sound;

/**
 * Enumeration of different sound types used in the game.
 */
public enum SoundType {
    OBSTACLE_HIT("obstacle_hit.wav"), // Sound played when an obstacle is hit
    TRIGGER_HIT("trigger_hit.wav"), // Sound played when a trigger is activated
    JUMP("jump.wav"), // Sound played when the player jumps
    GAME_OVER("game_over.wav"), // Sound played when the game is over
    GAME_WIN("game_win.wav"); // Sound played when the player wins the game

    private final String soundName; // The filename of the sound associated with this type

    /**
     * Constructor for the SoundType enum.
     * 
     * @param soundName The filename of the sound.
     */
    SoundType(String soundName) {
        this.soundName = soundName;
    }

    /**
     * Gets the filename of the sound associated with this SoundType.
     * 
     * @return The filename of the sound.
     */
    public String getSoundName() {
        return soundName;
    }
}
