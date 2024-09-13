package com.project.paradoxplatformer.utils.effect.api;

/**
 * Enum representing different game levels.
 * Each level is associated with a resource file used to load level data.
 */
public enum Level {
    EMPTY_LEVEL(""), // Represents an empty level with no associated resource file for placeholder
    LEVEL_ONE("level1.json"), // Represents the first level, using "level1.json" for its data
    LEVEL_TWO("level2.json"), // Represents the second level, using "level2.json" for its data
    LEVEL_THREE("level3.json"), // Represents the third level, using "level3.json" for its data
    LEVEL_FOUR("level4.json"); // Represents the fourth level, using "level4.json" for its data

    private final String resourceFile; // The file name of the resource associated with the level

    /**
     * Constructor for the Level enum.
     *
     * @param resourceFile the resource file associated with the level
     */
    Level(String resourceFile) {
        this.resourceFile = resourceFile;
    }

    /**
     * Gets the resource file associated with the level.
     *
     * @return the resource file name
     */
    public String getResourceFile() {
        return resourceFile;
    }
}
