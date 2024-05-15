package com.project.paradoxplatformer.model.player;

import com.project.paradoxplatformer.utils.entity.MutableObject;
import com.project.paradoxplatformer.utils.world.*;

public class Player implements MutableObject {

    private Point position;
    private Vector speed;
    private Dimension dimension;

    private double velocityX, velocityY;

    public Player(Point pos, Vector speed) {
        this.position = pos;
        this.speed = speed;
        this.dimension = new Dimension(16, 32); // TODO: modifica con valori sensati
    }

    public Player() {
        this.position = new Point(0, 0);

        this.speed = new Vector(0, 0);

        this.velocityX = 0;
        this.velocityY = 0;

        this.dimension = new Dimension(16, 32); // TODO: modifica con valori sensati
    }

    @Override
    public Point getPosition() {
        return new Point(position.x(), position.y());
    }

    public void setPosition(Point pos) {
        this.position = pos;
    }

    @Override
    public Vector getSpeed() {
        return this.speed;
    }

    public void setSpeed(Vector speed) {
        this.speed = speed;
    }

    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

    public void changeSize(int factorX, int factorY) {
        this.dimension = new Dimension(this.dimension.width() * factorX, this.dimension.height() * factorY);
    }

    @Override
    public void update(double dt) {
        this.position = this.position.sum(speed.mul(0.001 * dt));
    }

    public void setVelocity(double velocityX, double velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

}