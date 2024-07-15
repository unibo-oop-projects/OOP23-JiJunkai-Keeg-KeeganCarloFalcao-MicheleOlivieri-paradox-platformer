package com.project.paradoxplatformer.controller.deserialization.dtos;

public class LevelDTO {

    private double width;
    private double height;
    private final GameDTO[] gameDTOs;

    private LevelDTO(){
        this.gameDTOs = new GameDTO[0];
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public GameDTO[] getGameDTOs() {
        return gameDTOs;
    }

}
