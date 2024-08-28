package com.project.paradoxplatformer.utils.sound;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.ResourcesFinder;

public class SoundPathUtil {
    private static String BASE_PATH;

    static {
        BASE_PATH = "build/resources/main/com/project/paradoxplatformer/sounds/";

        // try {
        // BASE_PATH = ResourcesFinder.getURL("sounds/").toString();
        // } catch (InvalidResourceException e) {
        // e.printStackTrace();
        // }
    }

    public static String getPathForSound(SoundType soundType) {
        return BASE_PATH + soundType.getSoundName() + ".wav";
    }

}
