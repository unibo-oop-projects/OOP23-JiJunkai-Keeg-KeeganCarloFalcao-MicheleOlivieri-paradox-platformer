package com.project.paradoxplatformer.model.player;


import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.entity.dynamics.abstracts.AbstractControllableObject;
import com.project.paradoxplatformer.model.entity.dynamics.abstracts.HorizonalStats;
import com.project.paradoxplatformer.utils.geometries.*;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactory;
import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.modifiers.PhysicsEngine;
import com.project.paradoxplatformer.utils.geometries.vector.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public class PlayerModel extends AbstractControllableObject implements MutableObject {

    private Point position;
    private Dimension dimension;
    private PhysicsEngine movement;
    private Vector2D displacement;
    private final InterpolatorFactory interpFactory;

    //VECTORS ARE NOW VECTOR2d, Point is Coord2d
    //OBVisously any can modfiy their name to avoid further misunderstooding

    public PlayerModel(Coord2D pos, Dimension dimension, Vector2D speed) {
        //THIS IS REQUIRED CAUSE PLAYER CAN BE CONTROLLED BY USER
        super(new Simple2DVector(pos.x(), pos.y()), new HorizonalStats(70.d, 7.d));//addon
        movement = new PhysicsEngine();//addon
        interpFactory = new InterpolatorFactoryImpl();//addon
        this.position = new Point(pos.x(), pos.y());
        this.displacement = new Simple2DVector(pos.x(), pos.y());//addon
        this.horizontalSpeed = speed;//addon
        this.dimension = dimension; //TODO: modifica con valori sensati
    }

    @Override
    public Coord2D getPosition() {
        return new Coord2D(position.x(),position.y());
    }

    public void setPosition(Coord2D pos) {
        this.position = new Point(pos.x(), pos.y());
    }

    @Override
    public Vector2D getSpeed() {
        return this.horizontalSpeed;
    }

    //ADD ON
    public Vector getSp() {
        return this.speed;
    }

    public void setSpeed(Vector2D speed) {
        // this.speed = speed;
        this.horizontalSpeed = speed;//addon
    }

    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

    public void changeSize(double factorX, double factorY) {
        this.dimension = new Dimension(this.dimension.width()*factorX,this.dimension.height()*factorY);
    }

    @Override
    public void updateState(long dt) {
        //MY TESTING; FEEL FREE TO MODIFY
        this.fall();
        this.displacement = movement.step(this.displacement, 
            this.displacement.add(this.horizontalSpeed.add(verticalSpeed)),
            interpFactory.linear(),
            dt
        );//addon
        
        // this.position = this.position.sum(this.speed.mul(0.001*dt));
        this.setPosition(new Coord2D(this.displacement.xComponent(), this.displacement.yComponent()));//addon
    }

    //addon
    public void collectCoin() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'collectCoin'");
    }

}