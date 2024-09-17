package com.project.paradoxplatformer.model.player;

import java.util.Map;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;
import com.project.paradoxplatformer.model.entity.dynamics.abstracts.AbstractControllableObject;
import com.project.paradoxplatformer.model.entity.dynamics.abstracts.HorizonalStats;
import com.project.paradoxplatformer.model.entity.dynamics.behavior.PlatformJump;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactory;
import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.modifiers.PhysicsEngine;
import com.project.paradoxplatformer.utils.geometries.vector.api.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public final class PlayerModel extends AbstractControllableObject {

    // Definizioni costanti
    private static final Dimension DEFAULT_SIZE = new Dimension(10, 20);

    private static final int DEFAULT_ID = 0;

    // Proprietà del giocatore
    private Coord2D position;
    private Dimension dimension;
    private Vector2D displacement;

    // Sistema fisico e interpolazioni
    private PhysicsEngine physics;
    private InterpolatorFactory interpFactory;

    // Inventory
    private Inventory inventory;

    private Simple2DVector anchorVerticalPos;

    // Costruttore principale
    public PlayerModel(final int key, Coord2D pos, Dimension dimension) {
        super(key, new Simple2DVector(pos.x(), pos.y()), new HorizonalStats(150.d, 10)); 
        this.initialize(pos, dimension);
    }

    // Costruttore di default
    public PlayerModel() {
        this(DEFAULT_ID, Coord2D.origin(), DEFAULT_SIZE);
        this.setJumpBehavior(new PlatformJump());
    }

    // Metodo di inizializzazione comune ai costruttori
    private void initialize(Coord2D pos, Dimension dimension) {
        this.setPosition(pos);
        this.setDimension(dimension);
        this.displacement = new Simple2DVector(pos.x(), pos.y());
        this.horizontalSpeed = Polar2DVector.nullVector();
        this.verticalSpeed = Polar2DVector.nullVector();
        this.physics = new PhysicsEngine();
        this.interpFactory = new InterpolatorFactoryImpl();
        this.inventory = new SimpleInventory();
        this.anchorVerticalPos = new Simple2DVector(pos.x(), pos.y());
    }

    // Getters and setters per posizione e dimensioni
    @Override
    public Coord2D getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Coord2D pos) {
        this.position = new Coord2D(pos.x(), pos.y());
    }

    public void setDisplacement(Coord2D pos) {
        this.displacement = new Simple2DVector(pos.x(), pos.y());
    }

    public void setDisplacement(final double x) {
        this.displacement = new Simple2DVector(x, this.displacement.yComponent());
    }

    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

    @Override
    public void setDimension(Dimension dimension) {
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

    public void setSpeed(Vector2D speed) {
        this.horizontalSpeed = speed;
    }

    // Metodo per modificare la dimensione del player
    public void changeSize(double factorX, double factorY) {
        this.dimension = new Dimension(this.dimension.width() * factorX, this.dimension.height() * factorY);
    }

    // Metodo principale per aggiornare lo stato
    @Override
    public void updateState(long dt) {
        this.fall();
        System.out.println(verticalSpeed);
        handleHorizontalMovement(dt);
        handleVerticalMovement(dt);
        this.setPosition(this.displacement.convert());
        this.jumpBehavior.setFalling(true);
    }

    // Gestione del movimento orizzontale
    private void handleHorizontalMovement(long dt) {
        if (horizontalSpeed.magnitude() == this.getBaseDelta()) {
            this.horizontalSpeed = Polar2DVector.nullVector();
        }
        this.displacement = physics.step(this.displacement,
                this.displacement.add(this.horizontalSpeed),
                interpFactory.linear(),
                dt);
    }

    // Gestione del movimento verticale
    private void handleVerticalMovement(long dt) {
        
            var k = physics.moveTo(this.displacement,
                this.displacement.add(verticalSpeed), 1, interpFactory.easeIn(), dt);
            this.displacement = k.getKey();
        
        
    }

    // Metodo per la raccolta di oggetti
    public void collectItem(CollectableGameObject item) {
        this.inventory.addItem(item);
    }

    public Map<String, Long> getInventoryData() {
        return this.inventory.getItemsCounts();
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.PLAYER;
    }

    @Override
    public String toString() {
        return "Player: " + this.position + ", Inventory: " + this.getInventoryData();
    }

}