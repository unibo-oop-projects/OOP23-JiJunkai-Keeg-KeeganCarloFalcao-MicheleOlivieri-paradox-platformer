package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.trigger.Triggerable;

/**
 * Represents an obstacle in the game that can interact with other game objects.
 * An obstacle is a {@link MutableObject} that can be triggered by events and
 * has properties that can change over time.
 * 
 * <p>
 * It extends {@link MutableObject} to include position and dimension
 * properties, and {@link Triggerable} to support triggering mechanisms.
 * </p>
 */
public interface Obstacle extends MutableObject, Triggerable {

}
