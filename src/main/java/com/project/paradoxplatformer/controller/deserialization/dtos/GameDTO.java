package com.project.paradoxplatformer.controller.deserialization.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameDTO {

    @JsonProperty
    private String type;

    @JsonProperty
    private int x;

    @JsonProperty
    private int y;

    @JsonProperty
    private double width;

    @JsonProperty
    private double height;

    @JsonProperty
    private String subtype;

    @JsonProperty
    private String image;

    @JsonProperty
    private ColorDTO color;

    @JsonProperty
    private TrajMacro[] traj;

    public TrajMacro[] getTraj() {
        return traj;
    }

    public ColorDTO getColor() {
        return color;
    }

    public String getImage() {
        return image;
    }

    public GameDTO() {}

    public String getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getSubtype() {
        return this.subtype;
    }
    
}
