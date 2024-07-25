package com.project.paradoxplatformer.controller.input.api;

import java.util.Optional;

@FunctionalInterface
public interface InputTranslator<T> {
    Optional<InputType> translate(T t);
}
