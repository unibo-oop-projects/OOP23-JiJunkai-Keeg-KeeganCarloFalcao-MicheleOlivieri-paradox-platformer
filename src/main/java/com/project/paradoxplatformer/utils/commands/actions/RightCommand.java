package com.project.paradoxplatformer.utils.commands.actions;

import com.project.paradoxplatformer.utils.commands.actions.abstratcs.AbstractInvertedCommand;
import com.project.paradoxplatformer.utils.entity.dynamics.ControllableObject;

//add an invertable  interface ongly for left and right
public class RightCommand extends AbstractInvertedCommand {

    public RightCommand(final ControllableObject controllerObj) {
        super(controllerObj);
    }

    @Override
    public void execute() {
        if (this.isInverted) {
            this.controllerObj.moveRight();
        } else{
            this.controllerObj.moveLeft();
        }
    }
    
}