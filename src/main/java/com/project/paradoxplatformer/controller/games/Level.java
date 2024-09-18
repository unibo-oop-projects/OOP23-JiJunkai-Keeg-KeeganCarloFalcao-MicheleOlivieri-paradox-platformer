package com.project.paradoxplatformer.controller.games;

import com.project.paradoxplatformer.utils.ResourcesFinder;

/**
 * Enum representing different game levels.
 * Each level is associated with a resource file used to load level data.
 */
public enum Level {
    /**
     * Represents an empty level with no associated resource file for placeholder.
     */
    EMPTY_LEVEL(""),

    /**
     * Represents the first level, using "level1.json" for its data.
     */
    LEVEL_ONE(ResourcesFinder.JSON_FOLDER + "level1.json"),

    /**
     * Represents the second level, using "level2.json" for its data.
     */
    LEVEL_TWO(ResourcesFinder.JSON_FOLDER + "level2.json"),

    /**
     * Represents the third level, using "level3.json" for its data.
     */
    LEVEL_THREE(ResourcesFinder.JSON_FOLDER + "level3.json"),

    /**
     * Represents the fourth level, using "level4.json" for its data.
     */
    LEVEL_FOUR(ResourcesFinder.JSON_FOLDER + "level4.json");

    private final String resourceFile; // The file name of the resource associated with the level

    /**
     * Constructor for the Level enum.
     *
     * @param resourceFile the resource file associated with the level
     */
    Level(final String resourceFile) {
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

    /**
     * Returns the next level in sequence.
     * If it's the last level, it wraps around to the first level (excluding
     * EMPTY_LEVEL).
     *
     * @return the next level, wrapping around to LEVEL_ONE after the last level
     */
    public Level next() {
        final Level[] levels = Level.values();
        final int ordinal = this.ordinal();

        // Skip EMPTY_LEVEL and only consider valid levels
        if (this == EMPTY_LEVEL) {
            return LEVEL_ONE; // Start from LEVEL_ONE if currently EMPTY_LEVEL
        }

        // Return the next level, or wrap around to LEVEL_ONE if at the last level
        return ordinal >= levels.length - 1 ? LEVEL_ONE : levels[ordinal + 1];
    }
}
