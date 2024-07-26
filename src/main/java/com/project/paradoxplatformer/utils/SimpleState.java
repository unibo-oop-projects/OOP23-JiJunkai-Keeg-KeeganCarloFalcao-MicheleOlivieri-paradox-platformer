package com.project.paradoxplatformer.utils;

import java.util.Optional;
import java.util.function.Consumer;

public final class SimpleState implements State<String> {

    private String initial;
    private Optional<Consumer<String>> action;

    private SimpleState(String initial, Optional<Consumer<String>> action) {
        this.initial = initial;
        this.action = action;
    }

    public static State<String> create(String inital) {
        return new SimpleState(inital, Optional.empty());
    }

    @Override
    public State<String> copyOf(String newState) {
        return new SimpleState(newState, this.action);
    }

    @Override
    public String getValue() {
        //should return an immutable value
        return initial;
    }

    @Override
    public State<String> onSubscribe(Consumer<String> action) {
        return new SimpleState(initial, Optional.of(action));
    }

    @Override
    public void subscribe() {
        action.ifPresent(a -> a.accept(this.getValue()));
    }
    
}
