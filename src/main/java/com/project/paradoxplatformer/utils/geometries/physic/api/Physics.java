package com.project.paradoxplatformer.utils.geometries.physic.api;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.utils.geometries.interpolations.Interpolator;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * Represents an interface for managing 2D vector movements, typically modeling the motion 
 * from one point to another (e.g., moving from point A to point B) over time.
 * <p>
 * This interface is designed to handle various movement calculations, including both 
 * discrete and continuous movements, with or without a specified duration, and using 
 * interpolation techniques to manage how the motion is calculated over time.
 * </p>
 */
public interface Physics {

    /**
     * Performs a discretized movement from a start point to an end point over a given 
     * duration, using an interpolation method to calculate the progression of the motion.
     * This method breaks down the motion into steps based on the provided delta time (`dt`) 
     * and computes the intermediate position between the start and end vectors.
     * 
     * <p>The result is a pair consisting of:</p>
     * <ul>
     *   <li>The computed vector at the current time step.</li>
     *   <li>A percentage indicating the completion of the movement path (from 0.0 to 1.0), 
     *   where 1.0 means the end has been reached.</li>
     * </ul>
     * 
     * @param start the initial vector representing the start position of the movement
     * @param end the destination vector representing the end position of the movement
     * @param duration the total duration over which the movement should occur (in milliseconds)
     * @param interpType the interpolation type used to determine how the motion is calculated over time
     * @param dt the delta time representing the elapsed time for the current step (in milliseconds)
     * @return a {@link Pair} containing:
     *         <ul>
     *           <li>A {@link Vector2D} representing the current position at the given time step.</li>
     *           <li>A {@link Double} representing the completion percentage of the movement (0.0 to 1.0).</li>
     *         </ul>
     * @throws IllegalArgumentException if duration or delta time (`dt`) are non-positive values
     */
    Pair<Vector2D, Double> moveTo(
        Vector2D start, 
        Vector2D end,
        long duration,
        Interpolator<Vector2D> interpType,
        long dt
    );

    /**
     * Performs a step-by-step progression from a start vector to an end vector using the 
     * specified interpolation type. Unlike {@link #moveTo(Vector2D, Vector2D, long, Interpolator, long)},
     * this method does not operate over a fixed duration, meaning it calculates the position 
     * incrementally without regard to a total movement time.
     * 
     * <p>This method is useful for continuous or real-time applications where movement is 
     * updated incrementally at each time step, without predefined timing constraints.
     * </p>
     * 
     * @param start the initial vector representing the start position of the movement
     * @param end the destination vector representing the end position of the movement
     * @param interpType the interpolation type used to determine how the motion is calculated at each step
     * @param dt the delta time representing the time interval for the current step (in milliseconds)
     * @return a {@link Vector2D} representing the current position after applying the step at the given time step
     * @throws IllegalArgumentException if delta time (`dt`) is non-positive
     */
    Vector2D step(
        Vector2D start,
        Vector2D end,
        Interpolator<Vector2D> interpType,
        long dt
    );

    /**
     * Resets any internal state related to the current movement and stops further progression. 
     * 
     * <p>This method may clear any initialized fields or state, ensuring that further calls to 
     * movement methods such as {@link #moveTo(Vector2D, Vector2D, long, Interpolator, long)} 
     * or {@link #step(Vector2D, Vector2D, Interpolator, long)} do not continue from previous states.
     * </p>
     * 
     * @return a {@link Vector2D} representing the current or last known position when the movement was stopped
     */
    Vector2D stop();
}

