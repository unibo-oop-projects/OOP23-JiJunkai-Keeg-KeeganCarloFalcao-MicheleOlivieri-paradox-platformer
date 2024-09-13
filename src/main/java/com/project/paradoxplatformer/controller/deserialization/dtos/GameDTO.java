package com.project.paradoxplatformer.controller.deserialization.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object regarding a specific game object, most used in platform games.
 * Such class is used by the deserializer to retrive json objects, 
 * so they must not be normally initiliazed.
 * Such class holds every spec a game object can have, it is very related to the model entity, so such 
 * class must reflect model entity structure.
 */
public final class GameDTO {
    private String type;
    private double x;
    private double y;
    private double width;
    private double height;
    private String subtype;
    private String image;
    private ColorDTO color;
    private final TrajMacro[] traj;
    @JsonProperty 
    private SpriteDTO spriteMeta;

    /**
     * Non-argument constructors which initialises the trajectory moves, making it final.
     */
    public GameDTO() {
        this.traj = new TrajMacro[0];
    }

    /**
     * Holds sequential moves of a modded obstacle, such object mus be null if entity is not an obstacle nor
     * have a stored moving patterns.
     * @return an array containg the moves directions, should be handled as a queue, or even better an iterator
     */
    public TrajMacro[] getTraj() {
        return this.traj;
    }

    /**
     * Gets a game object color. Such method must be called in view segment
     * Usually blocks, such as platforms, walls, ecc are only colored.
     * <p> Note: It is paired with a rectangle view component meaning that a gameDTO which has a (non-null) 
     * color must not be an image nor an button ecc...<p>
     * @return relative DTO color
     */
    public ColorDTO getColor() {
        return this.color;
    }

    /**
     * Gets a game object image. Such method must be called in view segment
     * Usually player, special obstacles, triggers and exit doors have an image.
     * <p> Note: It is paired with a image view component meaning that such component must be able to
     * retrive an image interface based on the path<p>
     * @return the image file path, can be either .png, .jpeg. Note that such method does not provide a restriction upon file
     * extention, such responsabilty must be handled whoever creates an image based on file path
     */
    public String getImage() {
        return this.image;
    }

    /**
     * Gets the type of the game object which helps to track the game object supertype. 
     * It could be either player, obstacle, trigger...
     * 
     * @return the super type attribute
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the x coordinate.
     * @return an double (specific) coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the y coordinate.
     * @return an double (specific) coordinate
     */
    public double getY() {
        return this.y;
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
     * Gets the sub type of the obstacle which helps to identify the obstacle type. 
     * 
     * <p>NOTE: Such string must be equivalent to the obstacle type class name (CamelCase) cause it is used 
     * for reflection purposes. Such implementation could be avoided but on the other hand it would require
     * a mapping between classes and subtype which could result in boilerplate code.</p>
     * 
     * @return the super type attribute
     */
    public String getSubtype() {
        return this.subtype;
    }

    /**
     * Has a value greater than zero only if the image is a sprite.
     * It basically provides the minimum number of frames nedded to change the current sprite. It is useful to give a sense of
     * animation. Note that its frame management is different than the delta time and gameloop cause it is view dedicated section.
     * @return the minimum amount of frames for swithichng to next image
     */

    public SpriteDTO getSpriteMeta() {
        return spriteMeta;
    }
}
