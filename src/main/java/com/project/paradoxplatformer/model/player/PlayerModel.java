package com.project.paradoxplatformer.model.player;

import java.util.Map;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;
import com.project.paradoxplatformer.model.entity.dynamics.abstracts.AbstractControllableObject;
import com.project.paradoxplatformer.model.entity.dynamics.abstracts.HorizonalStats;
import com.project.paradoxplatformer.model.entity.dynamics.behavior.PlatformJump;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.*;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactory;
import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.modifiers.PhysicsEngine;
import com.project.paradoxplatformer.utils.geometries.modifiers.api.Physics;
import com.project.paradoxplatformer.utils.geometries.vector.api.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public final class PlayerModel extends AbstractControllableObject {

    private static final Dimension DEFAULT_SIZE = new Dimension(10, 20);
    private Coord2D position;
    private Dimension dimension;
    private Physics physics;
    private Vector2D displacement;
    private final InterpolatorFactory interpFactory;
    private final Inventory inventory;

    // VECTORS ARE NOW VECTOR2d, Point is Coord2d
    // OBVisously any can modfiy their name to avoid further misunderstooding

    public PlayerModel(Coord2D pos, Dimension dimension) {
        // THIS IS REQUIRED CAUSE PLAYER CAN BE CONTROLLED BY USER
        super(new Simple2DVector(pos.x(), pos.y()), new HorizonalStats(140.d, 14));// addon
        physics = new PhysicsEngine();// addon
        this.interpFactory = new InterpolatorFactoryImpl();// addon
        this.position = new Coord2D(pos.x(), pos.y());
        this.displacement = new Simple2DVector(pos.x(), pos.y());// addon
        this.horizontalSpeed = Polar2DVector.nullVector();// addon
        this.dimension = dimension;
        this.inventory = new SimpleInventory();
    }

    public PlayerModel() {
        this(Coord2D.origin(), DEFAULT_SIZE);
        this.setJumpBehavior(new PlatformJump());
    }

    @Override
    public Coord2D getPosition() {
        return new Coord2D(position.x(), position.y());
    }

    // COULD SHOW INTERNAL
    // PUBLIC FOR TESTING PURPOSE
    @Override
    public void setPosition(Coord2D pos) {
        this.position = new Coord2D(pos.x(), pos.y());
    }

    // ADD ON
    public Vector2D getSpeed() {
        return this.horizontalSpeed;
    }

    // COULD SHOW INTERNAL
    public void setSpeed(Vector2D speed) {
        // this.speed = speed;
        this.horizontalSpeed = speed;// addon
    }

    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

    public void changeSize(double factorX, double factorY) {
        this.dimension = new Dimension(this.dimension.width() * factorX, this.dimension.height() * factorY);
    }

    @Override
    public void updateState(long dt) {
        // MY TESTING; FEEL FREE TO MODIFY
        this.fall();
        this.displacement = physics.step(this.displacement,
                this.displacement.add(this.horizontalSpeed),
                interpFactory.linear(),
                dt);// addon

        var k = physics.moveTo(this.displacement,
                this.displacement.add(verticalSpeed), 1, interpFactory.easeIn(),
                dt);
        this.displacement = k.getKey();

        // this.position = this.position.sum(this.speed.mul(0.001*dt));
        this.setPosition(this.displacement.convert());// addon

    }

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
        return "Player: " + this.position + ", Inventory: " + this.getInventoryData() + " ";
    }

    @Override
    public void setDimension(Dimension dimension) {
        this.dimension = new Dimension(this.dimension.width(), this.dimension.height());
    }

}