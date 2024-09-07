package com.project.paradoxplatformer.controller.deserialization.dtos;

/**
 * A Data Transfer Objcet regarding a sequence of movements. It is exclusive to modded obstacle
 * so use with caution. It is very related to the game model, in fact the deserialization must reflect
 * the business model, in particular, this is a peculiar class to handle moving obstacle, which in most
 * games won't move.
 */
public final class TrajMacro {
    
    private final int x;
    private final int y;
    private final long duration;
    private final String vector;

    /**
     * Non argument constructor for making final fields, used by the json deserializer.
     */
    private TrajMacro() {
        this.x = 0;
        this.y = 0;
        this.duration = 0;
        this.vector = "";
    }

    /**
     * Coordinate for the destination position.
     * @return x cartesian 
     */
    public int getX() {
        return x;
    }
    
    /**
     * Coordinate for the destination position.
     * @return y cartesian 
     */
    public int getY() {
        return y;
    }
    
    /**
     * Helps to get the speed of the obsstacle, usually used by the physics engine.
     * @return duration of the animation
     */
    public long getDuration() {
        return duration;
    }
    
    /**
     * Represents the game enity trasforming part (height, width, displacement).
     * 
     * <p>Note that when height or width are used, according x or y must be set the same as the x or y position
     * @return TrasfType string enum analogue, it must be equal to the enum costant
     * @see {@link com.project.paradoxplatformer.model.entity.TrajectoryInfo}
     */
    public String getVector() {
        return vector;
    }
    
}
