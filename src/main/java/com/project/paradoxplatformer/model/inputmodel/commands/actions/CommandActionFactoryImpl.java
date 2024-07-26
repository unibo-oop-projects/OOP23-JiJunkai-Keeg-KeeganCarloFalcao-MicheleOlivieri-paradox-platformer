package com.project.paradoxplatformer.model.inputmodel.commands.actions;


import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;


public final class CommandActionFactoryImpl implements CommandActionFactory{

    public CommandActionFactoryImpl() {}
    
    public Command<ControllableObject> leftCommand() {
        return ControllableObject::moveLeft;
    }

    public Command<ControllableObject> upCommand() {
        return ControllableObject::jump;
    }

    public Command<ControllableObject> rightCommand() {
        return ControllableObject::moveRight;
    }
}