package com.project.paradoxplatformer.model.inputmodel.commands.actions;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;

public interface CommandActionFactory {
    
    Command<ControllableObject> leftCommand();

    Command<ControllableObject> upCommand();

    Command<ControllableObject> rightCommand();
}
