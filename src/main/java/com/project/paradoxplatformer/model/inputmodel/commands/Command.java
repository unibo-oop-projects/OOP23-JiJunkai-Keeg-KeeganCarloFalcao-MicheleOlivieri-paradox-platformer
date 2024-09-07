package com.project.paradoxplatformer.model.inputmodel.commands;

@FunctionalInterface
public interface Command<T> {
    void execute(T actor);
}
