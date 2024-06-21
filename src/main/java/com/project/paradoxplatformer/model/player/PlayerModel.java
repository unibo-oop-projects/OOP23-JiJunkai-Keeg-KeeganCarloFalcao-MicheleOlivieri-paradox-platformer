package com.project.paradoxplatformer.model.player;


import com.project.paradoxplatformer.utils.entity.MutableObject;
import com.project.paradoxplatformer.utils.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.entity.dynamics.abstracts.AbstractControllableObject;
import com.project.paradoxplatformer.utils.entity.dynamics.abstracts.HorizonalStats;
import com.project.paradoxplatformer.utils.world.*;
import com.project.paradoxplatformer.utils.world.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.world.interpolations.InterpolatorFactory;
import com.project.paradoxplatformer.utils.world.interpolations.InterpolatorFactoryImpl;
import com.project.paradoxplatformer.utils.world.modifiers.SimpleMovingModifer;
import com.project.paradoxplatformer.utils.world.vector.Simple2DVector;
import com.project.paradoxplatformer.utils.world.vector.api.Vector2D;

public class PlayerModel extends AbstractControllableObject implements MutableObject {

    private Coord2D position;
    private Vector2D speed;
    private Dimension dimension;
    private SimpleMovingModifer movement;
    private Vector2D displacement;
    private final InterpolatorFactory interpFactory;


    public PlayerModel(Coord2D pos, Vector2D speed) {
        super(new Simple2DVector(pos.x(), pos.y()), new HorizonalStats(70.d, 7.d));
        movement = new SimpleMovingModifer();
        interpFactory = new InterpolatorFactoryImpl();
        this.position = pos;
        this.displacement = new Simple2DVector(pos.x(), pos.y());
        this.speed = speed;
        this.dimension = new Dimension(16,32); //TODO: modifica con valori sensati
    }

    @Override
    public Coord2D getPosition() {
        return new Coord2D(position.x(),position.y());
    }

    public void setPosition(Coord2D pos) {
        this.position = pos;
    }

    @Override
    public Vector2D getSpeed() {
        return this.speed;
    }

    public void setSpeed(Vector2D speed) {
        this.speed = speed;
    }

    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

    public void changeSize(int factorX, int factorY) {
        this.dimension = new Dimension(this.dimension.width()*factorX,this.dimension.height()*factorY);
    }

    @Override
    public void updateState(long dt) {
        this.displacement = movement.step(this.displacement, 
            this.displacement.add(this.horizontalSpeed),
            interpFactory.linear(),
            dt
        );
        // this.position = this.position.sum(speed.mul(0.001*dt));
        this.setPosition(new Coord2D(this.displacement.xComponent(), this.displacement.yComponent()));
    }

}