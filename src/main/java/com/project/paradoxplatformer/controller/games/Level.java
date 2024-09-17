package com.project.paradoxplatformer.controller.games;

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
    LEVEL_ONE("level1.json"),

    /**
     * Represents the second level, using "level2.json" for its data.
     */
    LEVEL_TWO("level2.json"),

    /**
     * Represents the third level, using "level3.json" for its data.
     */
    LEVEL_THREE("level3.json"),

    /**
     * Represents the fourth level, using "level4.json" for its data.
     */
    LEVEL_FOUR("level4.json");

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
     * @return the next level, or null if it's the last level and no wrapping is
     *         desired
     */
    public Level next() {
        Level[] levels = Level.values();
        int ordinal = this.ordinal();

        // Skip EMPTY_LEVEL and only consider valid levels
        if (this == EMPTY_LEVEL || ordinal >= levels.length - 1) {
            return null; // Return null if already at the last valid level
        }

        return levels[ordinal + 1];
    }
}
