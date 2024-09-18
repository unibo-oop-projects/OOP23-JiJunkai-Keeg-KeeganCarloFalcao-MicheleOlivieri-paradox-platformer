package com.project.paradoxplatformer.model.player;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactory;
import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.physic.PhysicsEngine;
import com.project.paradoxplatformer.utils.geometries.vector.api.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * Manages the movement of an entity including horizontal and vertical speed.
 */
public class MovementController {

    private Vector2D horizontalSpeed;
    private Vector2D verticalSpeed;
    private Coord2D position;
    private final PhysicsEngine physics;
    private final InterpolatorFactory interpolatorFactory;

    /**
     * Constructs a MovementController with the given initial position and physics
     * engine.
     *
     * @param startPosition The initial position of the entity.
     * @param physicsEngine The physics engine used for movement calculations.
     */
    public MovementController(final Coord2D startPosition, final PhysicsEngine physicsEngine) {
        this.position = startPosition;
        this.physics = physicsEngine;
        this.interpolatorFactory = new InterpolatorFactoryImpl();
        this.horizontalSpeed = Polar2DVector.nullVector(); // Inizializzazione della velocità orizzontale
        this.verticalSpeed = Polar2DVector.nullVector(); // Inizializzazione della velocità verticale
    }

    /**
     * Updates the position based on the movement and elapsed time.
     *
     * @param dt The elapsed time in milliseconds since the last update.
     * @return The updated position.
     */
    public Coord2D updateMovement(final long dt) {
        // Interpolazione del movimento orizzontale
        final Vector2D newDisplacement = physics.step(this.toVector2D(position),
                this.toVector2D(position).add(horizontalSpeed),
                interpolatorFactory.linear(),
                dt);

        // Movimento verticale (es. salto o caduta)
        final Pair<Vector2D, Double> verticalMovement = physics.moveTo(newDisplacement,
                newDisplacement.add(verticalSpeed), 1, interpolatorFactory.easeIn(), dt);

        // Aggiorna la posizione del giocatore
        this.position = verticalMovement.getLeft().convert();
        return this.position;
    }

    /**
     * Sets the horizontal speed of the entity.
     *
     * @param speed The new horizontal speed.
     */
    public void setSpeed(final Vector2D speed) {
        this.horizontalSpeed = speed;
    }

    /**
     * Gets the current horizontal speed of the entity.
     *
     * @return The current horizontal speed.
     */
    public Vector2D getSpeed() {
        return this.horizontalSpeed;
    }

    /**
     * Stops the movement by setting both horizontal and vertical speeds to zero.
     */
    public void stopMovement() {
        this.horizontalSpeed = physics.stop();
        this.verticalSpeed = physics.stop();
    }

    /**
     * Converts a Coord2D position to a Vector2D.
     *
     * @param position The position to convert.
     * @return The converted Vector2D.
     */
    private Vector2D toVector2D(final Coord2D position) {
        return new Simple2DVector(position.x(), position.y());
    }
}
