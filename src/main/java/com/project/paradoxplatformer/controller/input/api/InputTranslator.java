package com.project.paradoxplatformer.controller.input.api;

@FunctionalInterface
public interface InputTranslator<T> {
    InputType translate(T t);
}
