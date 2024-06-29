package com.project.paradoxplatformer.utils.geometries.modifiers.api;

import com.project.paradoxplatformer.model.entity.dynamics.HorizontalObject;
import com.project.paradoxplatformer.model.entity.dynamics.VerticalObject;

public interface PatternModifier extends HorizontalObject, VerticalObject{
    
    public void pattern();

}
