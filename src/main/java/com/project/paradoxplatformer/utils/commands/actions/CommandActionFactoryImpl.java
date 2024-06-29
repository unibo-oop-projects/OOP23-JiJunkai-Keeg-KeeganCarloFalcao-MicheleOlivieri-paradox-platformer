package com.project.paradoxplatformer.utils.commands.actions;


import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.commands.Command;


public class CommandActionFactoryImpl implements CommandActionFactory{
    
    public Command leftCommand() {
        return ControllableObject::moveLeft;
    }

    public Command upCommand() {
        return ControllableObject::jump;
    }

    public Command rightCommand() {
        return ControllableObject::moveRight;
    }
}