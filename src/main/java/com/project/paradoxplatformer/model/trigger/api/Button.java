package com.project.paradoxplatformer.model.trigger.api;

import com.project.paradoxplatformer.model.trigger.AbstractTrigger;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * Represents a Button trigger in the game.
 * Extends from AbstractTrigger and can be used for various interactive
 * purposes.
 */
public class Button extends AbstractTrigger {

    /**
     * Constructor to create a Button with a specified position and dimension.
     *
     * @param position  the position of the Button in 2D space
     * @param dimension the size of the Button
     */
    public Button(Coord2D position, Dimension dimension) {
        super(position, dimension);
        this.position = position;
        this.dimension = dimension;
    }

    /**
     * Default constructor for debugging purposes.
     * Initializes the Button with a default position and dimension.
     */
    public Button() {
        super(new Coord2D(100, 200), new Dimension(50, 50));
    }

}
