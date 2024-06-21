package com.project.paradoxplatformer.utils.commands.actions;

import com.project.paradoxplatformer.utils.commands.Command;
import com.project.paradoxplatformer.utils.entity.dynamics.ControllableObject;

//add an invertable  interface ongly for left and right
public class UpCommand implements Command {

    private final ControllableObject controllerObj;

    public UpCommand(final ControllableObject controllerObj) {
        this.controllerObj = controllerObj;
    }

    @Override
    public void execute() {
        this.controllerObj.jump();
    }
    
}
