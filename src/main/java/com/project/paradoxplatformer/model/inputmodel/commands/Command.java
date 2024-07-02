package com.project.paradoxplatformer.model.inputmodel.commands;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;

@FunctionalInterface
public interface Command<T> {
    void execute(T actor);
}
