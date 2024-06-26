package com.project.paradoxplatformer;

public class ColorDTO {

    private int red;
    private int green;
    private int blu;
    private double alpha;

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlu() {
        return blu;
    }

    public void setBlu(int blu) {
        this.blu = blu;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public javafx.scene.paint.Color toFXColor() {
        return javafx.scene.paint.Color.rgb(this.red, this.green, this.blu, this.alpha);
    }

}
