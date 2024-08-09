package com.project.paradoxplatformer.model.obstacles;

import java.util.Optional;

import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.collision.api.Collidable;

public interface Obstacle extends MutableObject, Collidable {

    void effect(Optional<ControllableObject> ob);
}
