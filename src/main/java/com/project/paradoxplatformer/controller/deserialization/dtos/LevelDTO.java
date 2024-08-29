package com.project.paradoxplatformer.controller.deserialization.dtos;

/**
 * A level JSON file must be structured as follows: data for view segment and an array of game objects.
  * This is Data Transfer Object regarding a specific level implementation, most used in platform games.
   <p>It is the analogue of the {@link #World} class. Dimensions and Color are specific for view segment.</p>   
 */
public final class LevelDTO {

    private String type;
    private final double width;
    private final double height;
    private final GameDTO[] gameDTOs;

    /**
     * Non-argument Constructor for the purpose of having final fields.
     */
    private LevelDTO() {
        this.gameDTOs = new GameDTO[0];
        this.width = 0;
        this.height = 0;
    }

    /**
     * Gets the type of the game.
     * @return a String (specific)
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the default width dimension.
     * @return an double (specific) width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets the default height dimension.
     * @return an double (specific) height
     */
    public double getHeight() {
        return this.height;
    }


    /**
     * THe json file must be structered as a list of game objects.
     * @return an array of all game objects, could be any (player, obstacles, triggers, and more)
     */
    public GameDTO[] getGameDTOs() {
        return this.gameDTOs;
    }

}
