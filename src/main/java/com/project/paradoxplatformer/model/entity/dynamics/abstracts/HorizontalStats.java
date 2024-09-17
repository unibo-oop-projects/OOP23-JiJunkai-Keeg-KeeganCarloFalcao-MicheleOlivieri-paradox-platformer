package com.project.paradoxplatformer.model.entity.dynamics.abstracts;

/**
 * A record representing the horizontal movement statistics of an entity.
 * This includes the maximum movement limit and the rate of change in movement.
 * <p>
 * The {@code limit} specifies the maximum magnitude the entity's movement can
 * reach,
 * while the {@code delta} represents the rate at which the magnitude changes.
 * </p>
 * 
 * @param limit the maximum magnitude of horizontal movement
 * @param delta the rate of change in magnitude per movement
 */
public record HorizontalStats(double limit, double delta) {
}
