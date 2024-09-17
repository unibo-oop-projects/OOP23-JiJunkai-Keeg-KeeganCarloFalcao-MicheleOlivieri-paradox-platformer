package com.project.paradoxplatformer.utils;

import java.io.IOException;
import javax.imageio.ImageIO;

import javafx.scene.image.Image;

/**
 * Utility class for loading images.
 * This class provides static methods to load images in both JavaFX and AWT
 * formats.
 * It is designed as a utility class and should not be instantiated.
 */
public final class ImageLoader {

    // Private constructor to prevent instantiation
    private ImageLoader() {
        throw new UnsupportedOperationException("Image loader cannot be initialized");
    }

    /**
     * Loads an image as a JavaFX Image from the specified image path.
     *
     * @param imagePath the path to the image resource
     * @return the JavaFX Image object
     * @throws InvalidResourceException if the image resource cannot be found or
     *                                  loaded
     */
    public static Image createFXImage(final String imagePath) throws InvalidResourceException {
        return new Image(ResourcesFinder.getURL(imagePath).toExternalForm());
    }

    /**
     * Loads an image as a BufferedImage from the specified image path.
     *
     * @param imagePath the path to the image resource
     * @return the BufferedImage object
     * @throws IOException              if the image cannot be read by ImageIO
     * @throws InvalidResourceException if the image resource cannot be found or
     *                                  loaded
     */
    public static java.awt.image.BufferedImage createAWTImage(final String imagePath)
            throws IOException, InvalidResourceException {
        try {
            // Read the image from the resource URL
            var image = ImageIO.read(ResourcesFinder.getURL(imagePath));
            return image;
        } catch (IOException e) {
            // Wrap and rethrow the IOException with a descriptive message
            throw new IOException("Image could not be read by ImageIO", e);
        }
    }
}
