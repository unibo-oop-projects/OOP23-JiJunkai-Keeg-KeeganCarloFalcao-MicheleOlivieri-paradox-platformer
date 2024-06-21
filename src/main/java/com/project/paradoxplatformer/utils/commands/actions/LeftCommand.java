package com.project.paradoxplatformer.utils.commands.actions;

import com.project.paradoxplatformer.utils.commands.actions.abstratcs.AbstractInvertedCommand;
import com.project.paradoxplatformer.utils.entity.dynamics.ControllableObject;

//add an invertable  interface ongly for left and right
public class LeftCommand extends AbstractInvertedCommand {

    public LeftCommand(final ControllableObject controllerObj) {
        super(controllerObj);
    }

    @Override
    public void execute() {
        if (this.isInverted) {
            this.controllerObj.moveLeft();
        } else{
            this.controllerObj.moveRight();
        }
    }
    
}
