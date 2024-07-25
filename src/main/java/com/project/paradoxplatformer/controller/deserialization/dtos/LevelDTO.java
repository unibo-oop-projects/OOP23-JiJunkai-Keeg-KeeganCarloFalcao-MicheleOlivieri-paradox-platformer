package com.project.paradoxplatformer.controller.deserialization.dtos;

public final class LevelDTO {

    private final double width;
    private final double height;
    private final GameDTO[] gameDTOs;

    private LevelDTO(){
        this.gameDTOs = new GameDTO[0];
        this.width = 0;
        this.height = 0;
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
