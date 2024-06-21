package com.project.paradoxplatformer.utils.commands.actions.abstratcs;

import com.project.paradoxplatformer.utils.entity.dynamics.ControllableObject;

public abstract class AbstractInvertedCommand extends AbstractCommand{
    
    protected boolean isInverted;

    protected AbstractInvertedCommand(final ControllableObject controllerObj) {
        super(controllerObj);
        this.isInverted = false;
    }

    public void invert() {
        isInverted = !isInverted;
    }

    public abstract void execute();
}
