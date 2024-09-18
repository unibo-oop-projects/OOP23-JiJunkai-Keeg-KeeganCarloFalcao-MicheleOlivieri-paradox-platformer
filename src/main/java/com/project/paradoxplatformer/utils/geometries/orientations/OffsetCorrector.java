package com.project.paradoxplatformer.utils.geometries.orientations;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;


/**
 * Functional interface representing an offset correction strategy for adjusting the position of a 
 * 2D element ({@code Coord2D}) within a bounding box ({@code Dimension}). This interface defines a method 
 * that takes in the dimensions of a bounding box and the coordinates of an element and returns 
 * corrected coordinates based on a specific implementation of the correction logic.
 *
 * <p> This interface can be used in scenarios where 2D graphical elements need to be adjusted 
 * to fit properly within a specific region or when applying transformations such as centering, 
 * alignment, or other forms of coordinate correction.
 *
 * <p> As a functional interface, it can be used in lambda expressions or method references 
 * to succinctly define custom correction logic.
 *
 * <pre>{@code
 * // Example usage
 * OffsetCorrector centerCorrector = (box, element) -> new Coord2D(
 *     (box.getWidth() - element.getX()) / 2, 
 *     (box.getHeight() - element.getY()) / 2
 * );
 * 
 * Dimension boxDimension = new Dimension(200, 100);
 * Coord2D elementCoord = new Coord2D(50, 30);
 * Coord2D correctedCoord = centerCorrector.correct(boxDimension, elementCoord);
 * }</pre>
 *
 */
@FunctionalInterface
public interface OffsetCorrector {

    /**
     * Corrects the coordinates of an element within a specified bounding box.
     * 
     * @param boundingBox the {@link Dimension} representing the width and height of the bounding box.
     * @param elementCoord the {@link Coord2D} object representing the current coordinates of the element.
     * @return a new {@link Coord2D} object representing the corrected coordinates of the element within 
     *         the bounding box.
     */
    Coord2D correct(Dimension boundingBox, Coord2D elementCoord);

}

