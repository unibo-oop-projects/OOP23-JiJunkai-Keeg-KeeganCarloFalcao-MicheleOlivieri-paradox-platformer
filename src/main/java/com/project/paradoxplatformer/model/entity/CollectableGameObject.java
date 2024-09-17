package com.project.paradoxplatformer.model.entity;

/**
 * Represents a collectable game object in the game.
 * <p>
 * This interface extends {@link MutableObject} and adds a method to retrieve
 * the name of the object.
 * Objects implementing this interface can be collected by the player within the
 * game.
 * </p>
 */
public interface CollectableGameObject extends MutableObject {

    /**
     * Gets the name of the collectable game object.
     * <p>
     * The name can be used to identify or describe the object within the game.
     * </p>
     * 
     * @return the name of the collectable game object.
     */
    String getName();
}
