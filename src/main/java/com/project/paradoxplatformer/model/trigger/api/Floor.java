package com.project.paradoxplatformer.model.trigger.api;

import com.project.paradoxplatformer.model.trigger.AbstractTrigger;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * Represents an invisible trigger in the game, often used for detecting when
 * objects interact with the floor.
 * This class extends AbstractTrigger, and its main purpose is to function as
 * a trigger without any visible components. It can be used for handling events
 * related to floor interactions, like player landing or stepping on it.
 */
public class Floor extends AbstractTrigger {

    /**
     * Constructs a new Floor trigger with a specified position and dimension.
     * 
     * @param position  the position of the floor trigger in 2D space
     * @param dimension the size of the floor trigger
     */
    public Floor(Coord2D position, Dimension dimension) {
        super(position, dimension);
    }

    /**
     * Returns the type of collision this trigger represents, which is "FLOOR."
     *
     * @return the collision type as FLOOR
     */
    @Override
    public CollisionType getCollisionType() {
        return CollisionType.FLOOR;
    }
}
