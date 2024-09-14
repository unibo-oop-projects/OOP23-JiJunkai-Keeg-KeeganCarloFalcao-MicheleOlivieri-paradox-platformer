package com.project.paradoxplatformer.model.trigger;

import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * Represents a visible Button trigger in the game, typically used for
 * interactive purposes such as triggering obstacles.
 * 
 * <p>
 * This class extends {@link AbstractTrigger} and defines a button's position
 * and size. When activated, the button can trigger actions such as affecting
 * obstacles. The button is treated as a collidable object with the collision
 * type
 * {@link CollisionType#BUTTON}.
 * </p>
 */
public class Button extends AbstractTrigger {

    /**
     * Constructs a Button with the given position and size.
     * 
     * @param position  the position of the Button in 2D space
     * @param dimension the size (width and height) of the Button
     */
    public Button(Coord2D position, Dimension dimension) {
        super(position, dimension);
        this.position = position;
        this.dimension = dimension;
    }

    /**
     * Returns the collision type associated with this button.
     * 
     * @return the collision type, which is {@link CollisionType#BUTTON}
     */
    @Override
    public CollisionType getCollisionType() {
        return CollisionType.BUTTON;
    }
}
