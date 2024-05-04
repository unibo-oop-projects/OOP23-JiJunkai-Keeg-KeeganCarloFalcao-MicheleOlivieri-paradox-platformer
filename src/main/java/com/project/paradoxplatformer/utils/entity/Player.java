package com.project.paradoxplatformer.utils.entity;

import com.project.paradoxplatformer.utils.world.*;

public class Player implements MutableObject{

    private Point position;
    private Vector speed;

    protected Player(Point pos, Vector speed) {
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
    public Vector getSpeed() { return this.speed; }

    protected Vector setSpeed(Vector speed) {return this.speed = speed; }

}