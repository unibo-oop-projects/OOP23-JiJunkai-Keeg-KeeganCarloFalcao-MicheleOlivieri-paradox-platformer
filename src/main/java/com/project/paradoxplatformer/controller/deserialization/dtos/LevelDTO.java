package com.project.paradoxplatformer.controller.deserialization.dtos;

public class LevelDTO {

    private double width;
    private double height;
    private GameDTO[] gameDTOs;

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
    public GameDTO[] getGameDTOs() {
        return gameDTOs;
    }
    public void setGameDTOs(GameDTO[] gameDTOs) {
        this.gameDTOs = gameDTOs;
    }

}
