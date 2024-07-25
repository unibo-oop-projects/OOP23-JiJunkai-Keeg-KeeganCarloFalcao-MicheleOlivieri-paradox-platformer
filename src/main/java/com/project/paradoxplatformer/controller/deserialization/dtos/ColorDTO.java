package com.project.paradoxplatformer.controller.deserialization.dtos;

public final class ColorDTO {
    
    private final int red;
    private final int green;
    private final int blu;
    private final double alpha;

    public ColorDTO(int red, int green, int blu, double alfa) {
        this.red = red;
        this.green = green;
        this.blu = blu;
        this.alpha = alfa;
    }

    public ColorDTO() {
        this.red = 0;
        this.green = 0;
        this.blu = 0;
        this.alpha = 0;
    }

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
