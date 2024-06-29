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
    private TrajMacro[] traj;

    public TrajMacro[] getTraj() {
        return traj;
    }

    public void setTraj(TrajMacro[] traj) {
        this.traj = traj;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public ColorDTO getColor() {
        return color;
    }

    public void setColor(ColorDTO color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public GameDTO() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getSubtype() {
        return this.subtype;
    }
    
}
