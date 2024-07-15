package com.project.paradoxplatformer.controller.deserialization.dtos;


public class GameDTO {

    private String type;
    private int x;
    private int y;
    private double width;
    private double height;
    private String subtype;
    private String image;
    private ColorDTO color;
    private final TrajMacro[] traj;

    public TrajMacro[] getTraj() {
        return traj;
    }

    public ColorDTO getColor() {
        return color;
    }

    public String getImage() {
        return image;
    }

    public GameDTO() {
        this.traj = new TrajMacro[0];
    }

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
