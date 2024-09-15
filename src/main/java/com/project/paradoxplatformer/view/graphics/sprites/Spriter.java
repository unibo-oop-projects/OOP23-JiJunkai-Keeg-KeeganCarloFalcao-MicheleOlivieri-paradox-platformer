package com.project.paradoxplatformer.view.graphics.sprites;

import java.util.List;

/**
 * Defines a contract for objects that provide different sets of images for
 * various states of a sprite.
 * Implementations of this interface should supply images for different actions
 * or states of the sprite.
 *
 * @param <I> the type of the image or graphical representation used by the
 *            sprite
 */
public interface Spriter<I> {

    /**
     * Gets the list of images representing the idle state of the sprite.
     *
     * @return a list of images for the idle state
     */
    List<I> getIdleImage();

    /**
     * Gets the list of images representing the running state of the sprite.
     *
     * @return a list of images for the running state
     */
    List<I> runningImages();

    /**
     * Gets the list of images representing the jumping state of the sprite.
     *
     * @return a list of images for the jumping state
     */
    List<I> jumpingImages();

    /**
     * Gets the list of images representing the falling state of the sprite.
     *
     * @return a list of images for the falling state
     */
    List<I> fallingImages();
}
