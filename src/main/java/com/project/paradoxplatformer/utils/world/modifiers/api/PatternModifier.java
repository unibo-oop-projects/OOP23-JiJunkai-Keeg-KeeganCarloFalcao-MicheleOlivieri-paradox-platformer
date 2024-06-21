package com.project.paradoxplatformer.utils.world.modifiers.api;

import com.project.paradoxplatformer.utils.entity.dynamics.HorizontalObject;
import com.project.paradoxplatformer.utils.entity.dynamics.VerticalObject;

public interface PatternModifier extends HorizontalObject, VerticalObject{
    
    public void pattern();

}
