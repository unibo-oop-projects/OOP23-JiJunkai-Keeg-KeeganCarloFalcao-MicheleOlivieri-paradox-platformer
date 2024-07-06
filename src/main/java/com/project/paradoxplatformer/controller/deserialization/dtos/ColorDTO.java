package com.project.paradoxplatformer.controller.deserialization.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ColorDTO {

    @JsonProperty
    private int red;
    @JsonProperty
    private int green;
    @JsonProperty
    private int blu;
    @JsonProperty
    private double alpha;

    
    public ColorDTO(int red, int green, int blu, double alfa) {
        this.red = red;
        this.green = green;
        this.blu = blu;
        this.alpha = alfa;
    }

    public ColorDTO() {}

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlu() {
        return blu;
    }

    public double getAlpha() {
        return alpha;
    }


    public javafx.scene.paint.Color toFXColor() {
        return javafx.scene.paint.Color.rgb(this.red, this.green, this.blu, this.alpha);
    }

}
