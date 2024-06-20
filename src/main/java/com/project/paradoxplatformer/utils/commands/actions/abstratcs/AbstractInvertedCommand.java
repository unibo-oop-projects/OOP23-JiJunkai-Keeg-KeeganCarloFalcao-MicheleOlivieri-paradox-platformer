package com.project.paradoxplatformer.utils.commands.actions;

import com.project.paradoxplatformer.utils.entity.dynamics.ControllableObject;

public abstract class AbstractInvertedCommand extends AbstractCommand{
    
    protected boolean isInverted;

    protected AbstractInvertedCommand(final ControllableObject controllerObj) {
        super(controllerObj);
        this.isInverted = false;
    }

    public void invert() {
        isInverted = true;
    }

    public abstract void execute();
}
