package com.project.paradoxplatformer.view.graphics.sprites;

/**
 * Interface representing an entity that can have different animations based on
 * its status.
 * It allows for animating the entity and checking if it has special
 * characteristics.
 *
 * @param <S> The type of enumeration that represents different statuses or
 *            states for animation.
 */
public interface Spriteable<S extends Enum<S>> {

    /**
     * Animates the entity based on the provided status.
     *
     * @param status The status representing the current animation or state of the
     *               entity.
     */
    void animate(S status);

    /**
     * Checks if the entity is considered special (player usually).
     * Special entities might have unique properties or behaviors that differentiate
     * them from regular entities.
     *
     * @return {@code true} if the entity is special, {@code false} otherwise.
     */
    boolean isSpecial();
}
