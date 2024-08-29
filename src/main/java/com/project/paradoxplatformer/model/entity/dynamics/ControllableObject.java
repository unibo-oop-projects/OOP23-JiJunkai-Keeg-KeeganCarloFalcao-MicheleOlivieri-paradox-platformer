package com.project.paradoxplatformer.model.entity.dynamics;

import com.project.paradoxplatformer.model.entity.dynamics.behavior.JumpBehavior;


public interface ControllableObject extends HorizontalObject, VerticalObject{

    public void setJumpBehavior(JumpBehavior jb);

}
