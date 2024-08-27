package com.project.paradoxplatformer.utils.sound;

public class SoundPathUtil {
    private static final String BASE_PATH = "sounds/";

    public static String getPathForSound(SoundType soundType) {
        return BASE_PATH + soundType.getSoundName() + ".wav";
    }
}
