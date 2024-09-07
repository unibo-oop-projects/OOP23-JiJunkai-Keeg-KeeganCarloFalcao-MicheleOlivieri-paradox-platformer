package com.project.paradoxplatformer.utils.sound;

public enum SoundType {
    OBSTACLE_HIT("obstacle_hit.wav"),
    TRIGGER_HIT("trigger_hit.wav"),
    JUMP("jump.wav"),
    GAME_OVER("game_over.wav"),
    GAME_WIN("game_win.wav");

    private final String soundName;

    SoundType(String soundName) {
        this.soundName = soundName;
    }

    public String getSoundName() {
        return soundName;
    }
}
