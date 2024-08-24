package com.project.paradoxplatformer.utils.sound;

public enum SoundType {
    OBSTACLE_HIT("obstacle_hit"),
    TRIGGER_HIT("trigger_hit"),
    JUMP("jump"),
    GAME_OVER("game_over");

    private final String soundName;

    SoundType(String soundName) {
        this.soundName = soundName;
    }

    public String getSoundName() {
        return soundName;
    }
}
