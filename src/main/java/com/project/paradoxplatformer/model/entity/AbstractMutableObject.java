package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactory;
import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.modifiers.PhysicsEngine;

public abstract class AbstractMutableObject implements MutableObject {

    protected final PhysicsEngine mover;
    protected final InterpolatorFactory interFactory;
    protected boolean isIdle;

    protected AbstractMutableObject() {
        this.isIdle = true;
        this.mover = new PhysicsEngine();
        this.interFactory = new InterpolatorFactoryImpl();
    }

    @Override
    public abstract void updateState(long dt);
    
}
