package com.project.paradoxplatformer.model.player;

import com.project.paradoxplatformer.utils.entity.MutableObject;
import com.project.paradoxplatformer.utils.world.*;

public class PlayerModel implements MutableObject {

    private Point position;
    private Vector speed;

    public PlayerModel(Point pos, Vector speed) {
        this.position = pos;
        this.speed = speed;
    }

    @Override
    public Point getPosition() {
        return new Point(position.x(),position.y());
    }

    protected Point setPosition(Point pos) {
                return this.position = pos;
    }

    @Override
    public Vector getSpeed() {
        return this.speed;
    }

    protected Vector setSpeed(Vector speed) {
        return this.speed = speed;
    }

    @Override
    public void updateState(long dt) {
        this.position = this.position.sum(speed.mul(0.001*dt));
    }

}