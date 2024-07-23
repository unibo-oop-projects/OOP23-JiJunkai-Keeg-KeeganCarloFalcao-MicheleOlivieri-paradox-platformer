package com.project.paradoxplatformer.model.obstacles;

import java.util.Optional;

import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;

public interface Obstacle extends MutableObject{

    void effect(Optional<ControllableObject> ob);
}
