package com.project.paradoxplatformer.utils.effect.api;

public enum Level {
    EMPTY_LEVEL(""),
    LEVEL_ONE("level1.json"),
    LEVEL_TWO("level2.json"),
    LEVEL_THREE("level3.json"),
    LEVEL_FOUR("level4.json");

    private final String resourceFile;

    Level(String resourceFile) {
        this.resourceFile = resourceFile;
    }

    public String getResourceFile() {
        return resourceFile;
    }
}