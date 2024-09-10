package com.project.paradoxplatformer.model.entity.dynamics;

import com.project.paradoxplatformer.utils.geometries.modifiers.Direction;

public interface HorizontalObject{
    
    void moveLeft();

    void moveRight();

    void stop();

    double getBaseDelta();

    Direction direction();
}
