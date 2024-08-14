package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.entity.MutableObject;

public interface Obstacle extends MutableObject {

    boolean isCollectable();

    boolean isHarmful();
}