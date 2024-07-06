package com.project.paradoxplatformer.utils;

import java.io.IOException;
import javax.imageio.ImageIO;

import com.project.paradoxplatformer.ResourcesFinder;

import javafx.scene.image.Image;

public class ImageLoader {

    public ImageLoader() {
        throw new UnsupportedOperationException("Image loader cannot be initialized");
    }

    public static Image FXImage(final String imagePath) {
        
        return new Image(ResourcesFinder.getURL(imagePath).toExternalForm());
    }

    public static java.awt.image.BufferedImage AWTImage(final String imagePath) {
        try {
            return ImageIO.read(ResourcesFinder.getURL(imagePath));
        } catch (IOException e) {
            System.out.println("IMAGE NOT DOWNLOADED" + e.getCause());
        }
        return null;
    }
    
}
