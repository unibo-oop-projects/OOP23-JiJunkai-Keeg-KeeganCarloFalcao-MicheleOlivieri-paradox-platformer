package com.project.paradoxplatformer.utils;

import java.io.IOException;
import javax.imageio.ImageIO;

import javafx.scene.image.Image;

public final class ImageLoader {

    
    private ImageLoader() {
        throw new UnsupportedOperationException("Image loader cannot be initialized");
    }

    public static Image FXImage(final String imagePath) throws InvalidResourceException{
        return new Image(ResourcesFinder.getURL(imagePath).toExternalForm());
    }

    public static java.awt.image.BufferedImage AWTImage(final String imagePath) throws IOException, InvalidResourceException{
        try {
            var t = ImageIO.read(ResourcesFinder.getURL(imagePath));
            return t;
        } catch (IOException e) {
            throw new IOException("Image could not be read by ImageIO", e);
        }
    }
    
}
