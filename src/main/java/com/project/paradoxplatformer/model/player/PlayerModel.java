package com.project.paradoxplatformer.model.player;

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
 * Player model.
 */
public final class PlayerModel extends AbstractControllableObject implements InventoryManager{

    // Definizioni costanti
    private static final Dimension DEFAULT_SIZE = new Dimension(10, 20);

    private static final int DEFAULT_ID = 0;

    // Proprietà del giocatore
    private Coord2D position;
    private Dimension dimension;
    private Vector2D displacement;

    // Sistema fisico e interpolazioni
    private final PhysicsEngine physics;
    private final InterpolatorFactory interpFactory;

    // Inventory
    private final Inventory inventory;

    // Costruttore principale
    public PlayerModel(final int key, final Coord2D pos, final Dimension dimension) {
        super(key, new Simple2DVector(pos.x(), pos.y()), new HorizontalStats(150.d, 10)); 
        this.initialize(pos, dimension);
        this.physics = new PhysicsEngine();
        this.interpFactory = new InterpolatorFactoryImpl();
        this.inventory = new SimpleInventory();
    }

    // Costruttore di default
    public PlayerModel() {
        this(DEFAULT_ID, Coord2D.origin(), DEFAULT_SIZE);
        this.setJumpBehavior(new PlatformJump());
    }

    // Metodo di inizializzazione comune ai costruttori
    private void initialize(final Coord2D pos, final Dimension dimension) {
        this.setPosition(pos);
        this.setDimension(dimension);
        this.displacement = new Simple2DVector(pos.x(), pos.y());
        this.horizontalSpeed = Polar2DVector.nullVector();
        this.verticalSpeed = Polar2DVector.nullVector();
    }

    // Getters and setters per posizione e dimensioni
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

    public void setDisplacement(final Coord2D pos) {
        this.displacement = new Simple2DVector(pos.x(), pos.y());
    }

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

    // Metodi relativi alla velocità
    @Override
    public Vector2D getSpeed() {
        if (this.horizontalSpeed == null) {
            this.horizontalSpeed = Polar2DVector.nullVector(); // Default to null vector if uninitialized
        }
        return this.horizontalSpeed;
    }

    public void setSpeed(final Vector2D speed) {
        this.horizontalSpeed = speed;
    }

    // Metodo principale per aggiornare lo stato
    @Override
    public void updateState(final long dt) {
        this.fall();
        
        handleHorizontalMovement(dt);
        handleVerticalMovement(dt);
        this.setPosition(this.displacement.convert());
        this.jumpBehavior.setFalling(true);
    }

    // Metodo per la raccolta di oggetti
    @Override
    public void collectItem(final CollectableGameObject item) {
        this.inventory.addItem(item);
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.PLAYER;
    }

    /**
     * gets the description of the player. (position and inventory).
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

    // Gestione del movimento orizzontale
    private void handleHorizontalMovement(final long dt) {
        
        // if (horizontalSpeed.magnitude() == this.getBaseDelta()) {
        //     this.horizontalSpeed = Polar2DVector.nullVector();
        // }
        this.displacement = physics.step(this.displacement,
                this.displacement.add(this.horizontalSpeed),
                interpFactory.linear(),
                dt
            );
    }

    // Gestione del movimento verticale
    private void handleVerticalMovement(final long dt) {
        var nextVerticalDisplace = physics.moveTo(this.displacement,
            this.displacement.add(verticalSpeed),
            1,
            interpFactory.easeIn(), 
            dt
        );
        this.displacement = nextVerticalDisplace.getKey();
        
    }

}