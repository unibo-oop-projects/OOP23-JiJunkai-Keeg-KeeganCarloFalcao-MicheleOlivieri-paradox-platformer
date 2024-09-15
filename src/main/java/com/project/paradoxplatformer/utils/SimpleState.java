package com.project.paradoxplatformer.utils;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Implementation of {@link State} for managing a state with a {@link String}
 * value.
 * <p>
 * This class allows creating, copying, subscribing to, and retrieving the value
 * of the state.
 * </p>
 */
public final class SimpleState implements State<String> {

    private final String initial;
    private final Optional<Consumer<String>> action;

    /**
     * Constructs a {@link SimpleState} with the specified initial value and action.
     * <p>
     * The action is optional and can be executed when subscribing to the state.
     * </p>
     *
     * @param initial the initial value of the state
     * @param action  an optional action to execute upon subscription
     */
    private SimpleState(final String initial, final Optional<Consumer<String>> action) {
        this.initial = initial;
        this.action = action;
    }

    /**
     * Creates a new {@link SimpleState} with the given initial value.
     * <p>
     * The created state does not have any action associated with it initially.
     * </p>
     *
     * @param initial the initial value for the state
     * @return a new {@link SimpleState} instance
     */
    public static State<String> create(final String initial) {
        return new SimpleState(initial, Optional.empty());
    }

    @Override
    public State<String> copyOf(final String newState) {
        return new SimpleState(newState, this.action);
    }

    @Override
    public String getValue() {
        // Returns the immutable value
        return initial;
    }

    @Override
    public State<String> onSubscribe(final Consumer<String> action) {
        return new SimpleState(initial, Optional.of(action));
    }

    @Override
    public void subscribe() {
        action.ifPresent(a -> a.accept(this.getValue()));
    }
}
