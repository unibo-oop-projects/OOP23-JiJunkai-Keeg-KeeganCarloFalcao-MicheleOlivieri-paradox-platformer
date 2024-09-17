package com.project.paradoxplatformer.utils;

import java.util.function.Consumer;

/**
 * Represents a state that can be copied, subscribed to, and updated.
 * <p>
 * This interface defines methods for managing the state, including creating
 * copies of the state, subscribing to changes, and updating the state.
 * </p>
 *
 * @param <T> the type of the state value
 */
public interface State<T> {

    /**
     * Creates a copy of this state with a new value.
     * <p>
     * This method allows creating a new state instance with a different value
     * while preserving the original state.
     * </p>
     *
     * @param newState the new value for the copied state
     * @return a new {@link State} instance with the specified value
     */
    State<T> copyOf(T newState);

    /**
     * Registers an action to be executed when the state is subscribed to.
     * <p>
     * This method allows setting up an action that will be triggered upon
     * subscription.
     * </p>
     *
     * @param action the action to execute upon subscription
     * @return the current {@link State} instance
     */
    State<T> onSubscribe(Consumer<T> action);

    /**
     * Subscribes to the state.
     * <p>
     * This method triggers the subscription process and executes any registered
     * actions.
     * </p>
     */
    void subscribe();

    /**
     * Gets the current value of the state.
     * <p>
     * This method retrieves the value held by the state.
     * </p>
     *
     * @return the current value of the state
     */
    T getValue();

    /**
     * Updates the state with a new value if it differs from the current value.
     * <p>
     * This method creates a new state instance with the specified new value if
     * the new value is different from the current one; otherwise, it returns
     * the current state instance.
     * </p>
     *
     * @param newState the new value to update the state with
     * @return the updated {@link State} instance
     */
    default State<T> updateState(T newState) {
        if (this.getValue().equals(newState)) {
            return this;
        } else {
            return this.copyOf(newState);
        }
    }
}
