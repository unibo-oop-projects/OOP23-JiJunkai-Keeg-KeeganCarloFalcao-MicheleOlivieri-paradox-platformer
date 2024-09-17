package com.project.paradoxplatformer.view.javafx.fxcomponents;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.project.paradoxplatformer.controller.deserialization.dtos.SpriteDTO;
import com.project.paradoxplatformer.utils.ImageLoader;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.graphics.sprites.Spriter;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/**
 * A {@code Spriter} implementation that provides sprite images for different
 * states
 * by extracting images from a sprite sheet.
 */
public class FXSpriterSetter implements Spriter<Image> {

    private final String sheetPath;
    private final Dimension bounds;
    private Image img;
    private final Dimension tileSize;
    private final SpriteDTO spriteFrames;

    /**
     * Constructs a new {@code FXSpriterSetter} with the specified parameters.
     *
     * @param sheetPath  The path to the sprite sheet image.
     * @param bounds     The {@code Dimension} of the sprite sheet.
     * @param tileSize   The {@code Dimension} of each sprite tile.
     * @param spriteMeta The {@code SpriteDTO} containing sprite metadata.
     * @throws InvalidResourceException If the sprite sheet cannot be loaded.
     */
    public FXSpriterSetter(
            final String sheetPath,
            final Dimension bounds,
            final Dimension tileSize,
            final SpriteDTO spriteMeta) throws InvalidResourceException {
        this.sheetPath = sheetPath;
        this.bounds = bounds;
        this.tileSize = tileSize;
        this.loadSpriteSheet();
        this.spriteFrames = spriteMeta;
    }

    /**
     * {@inheritDoc}
     * Returns a list of images representing the idle state of the sprite.
     */
    @Override
    public List<Image> getIdleImage() {
        return this.collection(0, tileSize.width() * spriteFrames.getIdleFrames());
    }

    /**
     * {@inheritDoc}
     * Returns a list of images representing the running state of the sprite.
     */
    @Override
    public List<Image> runningImages() {
        return this.collection(
                spriteFrames.getIdleFrames() * tileSize.width(),
                tileSize.width() * spriteFrames.getRunningFrames());
    }

    /**
     * {@inheritDoc}
     * Returns a list containing a single image for the jumping state of the sprite.
     */
    @Override
    public List<Image> jumpingImages() {
        return List.of(this.img);
    }

    /**
     * {@inheritDoc}
     * Returns a list containing a single image for the falling state of the sprite.
     */
    @Override
    public List<Image> fallingImages() {
        return List.of(this.img);
    }

    /**
     * Loads the sprite sheet image from the specified path.
     *
     * @throws InvalidResourceException If the sprite sheet cannot be loaded.
     */
    private void loadSpriteSheet() throws InvalidResourceException {
        this.img = ImageLoader.createFXImage(sheetPath);
    }

    /**
     * Creates a list of images from a sprite sheet within the specified range.
     *
     * @param init The starting x-coordinate of the first tile.
     * @param end  The x-coordinate after the last tile.
     * @return A list of images from the sprite sheet.
     */
    private List<Image> collection(final double init, final double end) {
        return Optional.of(this.img)
                .filter(j -> tileSize.width() == bounds.width())
                .map(List::of)
                .orElse(Stream.iterate(init, x -> x < end, x -> x + tileSize.width())
                        .map(this::createImage)
                        .collect(Collectors.toList()));
    }

    /**
     * Creates a new image from the sprite sheet at the specified x-coordinate.
     *
     * @param x The x-coordinate of the image in the sprite sheet.
     * @return The created {@code Image}.
     */
    private Image createImage(final Double x) {
        return new WritableImage(
                this.img.getPixelReader(),
                x.intValue(),
                0,
                (int) this.tileSize.width(),
                (int) this.tileSize.height());
    }
}
