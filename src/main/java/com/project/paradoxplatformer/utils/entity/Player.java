package com.project.paradoxplatformer.utils.entity;

import com.project.paradoxplatformer.utils.world.*;

public class Player implements GameObject{

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

    @Override
    public Point setPosition(Point pos) {
        return this.position = pos;
    }
}
