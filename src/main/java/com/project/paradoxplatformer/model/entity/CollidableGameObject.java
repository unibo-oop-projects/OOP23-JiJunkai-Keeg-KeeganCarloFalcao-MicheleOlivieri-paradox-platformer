package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.collision.api.Collidable;

/**
 * Represents a game object that can participate in collision detection.
 * 
 * This interface extends both {@link GameObject} and {@link Collidable},
 * indicating
 * that an object implementing this interface is not only a game entity but also
 * has the capability to be involved in collision interactions.
 */
public interface CollidableGameObject extends GameObject, Collidable {

}
