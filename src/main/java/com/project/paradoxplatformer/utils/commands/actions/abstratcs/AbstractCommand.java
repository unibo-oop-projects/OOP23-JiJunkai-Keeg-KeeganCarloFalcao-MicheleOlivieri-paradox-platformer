package com.project.paradoxplatformer.utils.commands.actions.abstratcs;

import com.project.paradoxplatformer.model.menu.api.Command;
import com.project.paradoxplatformer.utils.entity.dynamics.ControllableObject;

public abstract class AbstractCommand implements Command{

    protected final ControllableObject controllerObj;

    public AbstractCommand(final ControllableObject controllerObj) {
        this.controllerObj = controllerObj;
    }

    @Override
    public abstract void execute();
}
