package com.project.paradoxplatformer.controller.deserialization.dtos;

/**
 * Data Transfer Object regarding a generic color with alha channel.
 * Such class is used by the deserializer to retrive json objects, 
 * so they must not be normally initiliazed.
 * RGD are ranged between 0-255 while alpha (opacity) 0-1
 */
public final class ColorDTO {
    private final int red;
    private final int green;
    private final int blu;
    private final double alpha;

    /**
     * Used by the deserializer in case color object was null.
     * Such constructor makes fiedls final
     */
    public ColorDTO() {
        this.red = 0;
        this.green = 0;
        this.blu = 0;
        this.alpha = 0;
    }

    /**
     * Returns the red component in the range 0-255.
     * @return the red component
     */
    public int getRed() {
        return this.red;
    }

    /**
     * Returns the green component in the range 0-255.
     * @return the green component
     */
    public int getGreen() {
        return this.green;
    }

    /**
     * Returns the blue component in the range 0-255.
     * @return the blue component
     */
    public int getBlu() {
        return this.blu;
    }

    /**
     * Returns the alpha component in the range 0-1, sets opacity.
     * @return the aplha channel, note that is double
     */
    public double getAlpha() {
        return this.alpha;
    }

    /**
     * Converts a {@link ColorDTO} to an javaFX color interface.
     * Utiliy method, meaning the helps the client
     * @return a JavaFX converted color
     */
    public javafx.scene.paint.Color toFXColor() {
        return javafx.scene.paint.Color.rgb(this.red, this.green, this.blu, this.alpha);
    }

    /**
     * Converts a {@link ColorDTO} to an AWT (so Swing) color interface.
     * Utiliy method, meaning the helps the client
     * @return a AWT converted color
     */
    public java.awt.Color toAwtColor() {
        return new java.awt.Color(this.red, this.green, this.blu);
    }
}
