package com.project.paradoxplatformer.utils.commands;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;

@FunctionalInterface
public interface Command {
    void execute(ControllableObject actor);
}
