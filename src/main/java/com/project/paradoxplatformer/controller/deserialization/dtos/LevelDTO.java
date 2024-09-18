package com.project.paradoxplatformer.controller.deserialization.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A level JSON file must be structured as follows: data for view segment and an
 * array of game objects.
 * This is Data Transfer Object regarding a specific level implementation, most
 * used in platform games.
 * <p>
 * It is the analogue of the
 * {@link com.project.paradoxplatformer.model.world.api.World} class. Dimensions
 * and Color are
 * specific for view segment.
 * </p>
 */
public final class LevelDTO {

    private String type;
    private final double width;
    private final double height;
    @JsonProperty
    private final String backgroundFile;

    private final GameDTO[] gameDTOs;

    /**
     * Constructs a LevelDTO object with the specified parameters.
     *
     * @param type           the type of the level, indicating its category or
     *                       purpose
     * @param width          the width of the level in units (e.g., pixels, meters)
     * @param height         the height of the level in units (e.g., pixels, meters)
     * @param backgroundFile the file path or name of the background image for the
     *                       level
     * @param gameDTOs       an array of {@link GameDTO} objects representing the
     *                       game elements in the level;
     *                       if {@code null}, initializes with an empty array
     */
    @JsonCreator
    public LevelDTO(
            @JsonProperty("type") final String type,
            @JsonProperty("height") final double height,
            @JsonProperty("width") final double width,
            @JsonProperty("backgroundFile") final String backgroundFile,
            @JsonProperty("gameDTOs") final GameDTO[] gameDTOs) {
        this.type = type;
        this.width = width;
        this.height = height;
        this.backgroundFile = backgroundFile == null ? "": backgroundFile;
        this.gameDTOs = gameDTOs != null ? gameDTOs.clone() : new GameDTO[0];
    }

    /**
     * Constructs a {@link LevelDTO} with the specified width, height, and game data transfer objects (DTOs).
     * <p>
     * This constructor initializes a level with the given dimensions and a list of game DTOs. The background file
     * is set to an empty string by default.
     * </p>
     * 
     * @param width      the width of the level
     * @param height     the height of the level
     * @param gameDTOs   an array of {@link GameDTO} objects representing the game elements in the level
     */
    public LevelDTO(final int width, final int height, final GameDTO[] gameDTOs) {
        this.width = width;
        this.height = height;
        this.gameDTOs = gameDTOs;
        this.backgroundFile = "";
    }



    /**
     * Gets the type of the game.
     * 
     * @return a String (specific)
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the default width dimension.
     * 
     * @return an double (specific) width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets the default height dimension.
     * 
     * @return an double (specific) height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * THe json file must be structered as a list of game objects.
     * 
     * @return an array of all game objects, could be any (player, obstacles,
     *         triggers, and more)
     */
    public GameDTO[] getGameDTOs() {
        return this.gameDTOs.clone();
    }

    /**
     * Gets the background image file.
     * 
     * @return the background image file path.
     */
    public String getBackgroundFile() {
        return backgroundFile;
    }

}
