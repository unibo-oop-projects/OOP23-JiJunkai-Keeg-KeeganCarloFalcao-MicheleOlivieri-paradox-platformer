package com.project.paradoxplatformer.controller.deserialization.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;

public class LevelDTO {

    @JsonProperty
    private double width;

    @JsonProperty
    private double height;

    @JsonProperty
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
