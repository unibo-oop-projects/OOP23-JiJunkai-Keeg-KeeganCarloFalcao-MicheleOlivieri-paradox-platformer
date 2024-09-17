package com.project.paradoxplatformer.view.javafx.fxcomponents;

import com.project.paradoxplatformer.controller.deserialization.dtos.SpriteDTO;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.graphics.sprites.SpriteAnimator;
import com.project.paradoxplatformer.view.graphics.sprites.SpriteStatus;
import com.project.paradoxplatformer.view.graphics.sprites.Spriteable;

import javafx.scene.image.Image;

/**
 * An adapter that integrates a sprite image with animation capabilities.
 * Extends {@code FXImageAdapter} to include sprite animation and special status
 * handling.
 */
public final class FXSpriteAdapter extends FXImageAdapter implements Spriteable<SpriteStatus> {

    private SpriteAnimator<Image> spriteAnimator;
    private boolean isSpecial;

    /**
     * Constructs a new {@code FXSpriteAdapter} with the given parameters.
     * @param id the unique id of the button
     * @param dimension  The {@code Dimension} of the sprite.
     * @param position   The {@code Coord2D} position of the sprite.
     * @param imageURL   The URL of the image to use for the sprite.
     * @param spriteMeta The {@code SpriteDTO} containing metadata for sprite
     *                   animation and special status.
     * @throws InvalidResourceException If the provided image URL is invalid or if
     *                                  there are issues with loading the image.
     */
    protected FXSpriteAdapter(final int id, 
        final Dimension dimension, 
        final Coord2D position, 
        final String imageURL, 
        final SpriteDTO spriteMeta
    ) throws InvalidResourceException {
        super(id, dimension, position, imageURL);
        this.spriteAnimator = new SpriteAnimator<>(
                new FXSpriterSetter(
                        imageURL,
                        new Dimension(
                                getImageView().getImage().getWidth(),
                                getImageView().getImage().getHeight()),
                        dimension,
                        spriteMeta),
                spriteMeta.getMinFrames());
        this.isSpecial = spriteMeta.isSpecial();
    }

    /**
     * Animates the sprite based on the given {@code SpriteStatus}.
     * Updates the sprite's image according to the current animation frame.
     *
     * @param status The {@code SpriteStatus} indicating the current animation state
     *               (e.g., running, jumping).
     */
    @Override
    public void animate(final SpriteStatus status) {
        this.spriteAnimator.selectFrame(status, getImageView()::setImage);
    }

    /**
     * Checks if this sprite is considered special.
     *
     * @return {@code true} if the sprite is special, {@code false} otherwise.
     */
    @Override
    public boolean isSpecial() {
        return this.isSpecial;
    }
}
