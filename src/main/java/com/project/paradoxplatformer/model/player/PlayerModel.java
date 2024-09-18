package com.project.paradoxplatformer.model.player;

import java.util.Optional;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;
import com.project.paradoxplatformer.model.entity.dynamics.abstracts.AbstractControllableObject;
import com.project.paradoxplatformer.model.entity.dynamics.abstracts.HorizontalStats;
import com.project.paradoxplatformer.model.entity.dynamics.behavior.PlatformJump;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactory;
import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.physic.PhysicsEngine;
import com.project.paradoxplatformer.utils.geometries.vector.api.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * Player model that handles the player's state, position, movement,
 * and inventory management.
 */
public final class PlayerModel extends AbstractControllableObject implements InventoryManager {

    // Definizioni costanti
    private static final Dimension DEFAULT_SIZE = new Dimension(10, 20);
    private static final int DEFAULT_ID = 0;
    private static final double DEFAULT_SPEED = 150.0; // Magic number replaced

    // Proprietà del giocatore
    private Coord2D position;
    private Dimension dimension;
    private Vector2D displacement;

    // Sistema fisico e interpolazioni
    private final PhysicsEngine physics;
    private final InterpolatorFactory interpFactory;

    // Inventory
    private final Inventory inventory;

    /**
     * Constructs a PlayerModel with a given key, position, and dimension.
     *
     * @param key       The identifier of the player.
     * @param pos       The initial position of the player.
     * @param dimension The dimension of the player.
     */
    public PlayerModel(final int key, final Coord2D pos, final Dimension dimension) {
        super(key, new HorizontalStats(DEFAULT_SPEED, 10));
        this.initialize(pos, dimension);
        this.physics = new PhysicsEngine();
        this.interpFactory = new InterpolatorFactoryImpl();
        this.inventory = new SimpleInventory();
    }

    /**
     * Constructs a PlayerModel with default values.
     */
    public PlayerModel() {
        this(DEFAULT_ID, Coord2D.origin(), DEFAULT_SIZE);
        this.setJumpBehavior(Optional.of(new PlatformJump()));
    }

    /**
     * Initializes the player's position and dimension.
     *
     * @param pos       The initial position.
     * @param dimension The dimension of the player.
     */
    private void initialize(final Coord2D pos, final Dimension dimension) {
        this.setPosition(pos);
        this.setDimension(dimension);
        this.displacement = new Simple2DVector(pos.x(), pos.y());
        setHorizontalSpeed(Polar2DVector.nullVector());
        setVerticalSpeed(Polar2DVector.nullVector());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Coord2D getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Coord2D pos) {
        this.position = new Coord2D(pos.x(), pos.y());
    }

    /**
     * Sets the displacement of the player using the specified position.
     *
     * @param pos The new position to set as displacement.
     */
    public void setDisplacement(final Coord2D pos) {
        this.displacement = new Simple2DVector(pos.x(), pos.y());
    }

    /**
     * Sets the horizontal displacement of the player.
     *
     * @param x The new horizontal displacement value.
     */
    public void setDisplacement(final double x) {
        this.displacement = new Simple2DVector(x, this.displacement.yComponent());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDimension(final Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getSpeed() {
        if (getHorizontalSpeed() == null) {
            setHorizontalSpeed(Polar2DVector.nullVector()); // Default to null vector if uninitialized
        }
        return getHorizontalSpeed();
    }

    /**
     * Sets the speed of the player.
     *
     * @param speed The new speed to set.
     */
    public void setSpeed(final Vector2D speed) {
        setHorizontalSpeed(speed);
    }

    /**
     * Updates the state of the player based on the elapsed time.
     * <p>
     * This method is responsible for updating the player's position and movement
     * state by
     * incorporating various types of movements: falling, horizontal, and vertical.
     * The method
     * first handles the falling logic, which may affect the player's vertical
     * displacement due
     * to gravity or other falling mechanisms. Next, it updates the player's
     * horizontal movement
     * based on the elapsed time and current speed. Then, it processes the vertical
     * movement to
     * update the player's position accurately. Finally, it updates the player's
     * position in the
     * game world and sets the falling state in the jump behavior to indicate that
     * the player is
     * currently falling.
     * </p>
     * 
     * <p>
     * This method should be called regularly, typically once per game loop
     * iteration, to ensure
     * smooth and accurate movement of the player character.
     * </p>
     *
     * @param dt the elapsed time in milliseconds since the last update. This value
     *           is used to
     *           calculate how far the player has moved horizontally and vertically
     *           during
     *           the time interval.
     */
    @Override
    public void updateState(final long dt) {
        this.fall();
        handleHorizontalMovement(dt);
        handleVerticalMovement(dt);
        this.setPosition(this.displacement.convert());
        getJumpBehavior().setFalling(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collectItem(final CollectableGameObject item) {
        this.inventory.addItem(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionType getCollisionType() {
        return CollisionType.PLAYER;
    }

    /**
     * Gets the description of the player, including position and inventory.
     *
     * @return A string representation of the player's state.
     */
    @Override
    public String toString() {
        return "Player: " + this.position + ", Inventory: " + this.inventory.getItemsCounts();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory getInventory() {
        return new SimpleInventory(this.inventory.getImmutableItems());
    }

    /**
     * Handles the horizontal movement of the player based on the elapsed time.
     * 
     * <p>
     * It a step movement as it works with user input so they are steps and not
     * paths
     * </p>
     *
     * @param dt The elapsed time since the last update.
     */
    private void handleHorizontalMovement(final long dt) {
        this.displacement = physics.step(this.displacement,
                this.displacement.add(getHorizontalSpeed()),
                interpFactory.linear(),
                dt);
    }

    /**
     * Handles the vertical movement of the player based on the elapsed time.
     *
     * @param dt The elapsed time since the last update.
     */
    private void handleVerticalMovement(final long dt) {
        final var nextVerticalDisplace = physics.moveTo(this.displacement,
                this.displacement.add(getVerticalSpeed()),
                1,
                interpFactory.easeIn(),
                dt);
        this.displacement = nextVerticalDisplace.getKey();
    }
}
