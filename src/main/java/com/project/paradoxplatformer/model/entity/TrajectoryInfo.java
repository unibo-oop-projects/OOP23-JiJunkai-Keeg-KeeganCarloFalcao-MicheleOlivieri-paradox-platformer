package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * Represents information about a trajectory for an entity.
 * <p>
 * This record contains details about the endpoint, duration, and type of
 * transformation
 * applied to an entity's trajectory.
 * </p>
 * 
 * @param endpoint   The end point of the trajectory as a {@link Vector2D}.
 * @param duration   The duration of the trajectory in milliseconds.
 * @param transfType The type of transformation applied to the trajectory as a
 *                   {@link TrasformType}.
 */
public final record TrajectoryInfo(Vector2D endpoint, long duration, TrasformType transfType) {

}
