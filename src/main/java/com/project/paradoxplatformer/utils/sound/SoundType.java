package com.project.paradoxplatformer.utils.sound;

/**
 * Enumeration of different sound types used in the game.
 */
public enum SoundType {
    /**
     * Sound played when an obstacle is hit.
     */
    OBSTACLE_HIT("obstacle_hit.wav"),

    /**
     * Sound played when a trigger is activated.
     */
    TRIGGER_HIT("trigger_hit.wav"),

    /**
     * Sound played when the player jumps.
     */
    JUMP("jump.wav"),

    /**
     * Sound played when the game is over.
     */
    GAME_OVER("game_over.wav"),

    /**
     * Sound played when the player wins the game.
     */
    GAME_WIN("game_win.wav");

    private final String soundName; // The filename of the sound associated with this type

    /**
     * Constructor for the SoundType enum.
     * 
     * @param soundName The filename of the sound.
     */
    SoundType(final String soundName) {
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
