package com.project.paradoxplatformer.controller.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * EventManager provides a mechanism for communication between different classes
 * without passing instances between them. It follows the Singleton pattern to
 * ensure that only one instance manages all events across the application.
 *
 * @param <T> The type of event identifiers.
 * @param <U> The type of the first parameter for event handlers.
 */
public final class EventManager<T, U> {

    private final Map<T, BiConsumer<U, ?>> eventMap = new HashMap<>(); // Map to store event handlers

    // Private constructor to prevent direct instantiation
    private EventManager() {
    }

    /**
     * Gets the singleton instance of the EventManager.
     *
     * @param <T> The type of event identifiers.
     * @param <U> The type of the first parameter for event handlers.
     * @return The singleton instance of the EventManager.
     */
    @SuppressWarnings("unchecked") // Suppressing unchecked cast warning because the type is managed by the
                                   // consumer
    public static <T, U> EventManager<T, U> getInstance() {
        return (EventManager<T, U>) Holder.INSTANCE;
    }

    /**
     * Subscribes an event handler to a specific event type.
     *
     * @param eventType The type of event to subscribe to.
     * @param action    The action to perform when the event is published.
     * @param <V>       The type of the second parameter for event handlers.
     */
    public <V> void subscribe(final T eventType, final BiConsumer<U, V> action) {
        eventMap.put(eventType, action);
    }

    /**
     * Publishes an event, triggering all subscribed handlers.
     *
     * @param eventType The type of event to publish.
     * @param param1    The first parameter to pass to event handlers.
     * @param param2    The second parameter to pass to event handlers.
     * @param <V>       The type of the second parameter for event handlers.
     */
    @SuppressWarnings("unchecked") // Suppressing unchecked cast warning because the type is managed by the
                                   // consumer
    public <V> void publish(final T eventType, final U param1, final V param2) {
        Optional.of(eventType)
                .filter(eventMap::containsKey)
                .map(eventMap::get)
                .ifPresent(f -> ((BiConsumer<U, V>) f).accept(param1, param2));
    }

    /**
     * Unsubscribes from an event type, removing the associated event handler.
     *
     * @param eventType The type of event to unsubscribe from.
     */
    public void unsubscribe(final T eventType) {
        this.eventMap.remove(eventType);
    }

    // Static inner class responsible for holding the Singleton instance
    private static class Holder {
        private static final EventManager<?, ?> INSTANCE = new EventManager<>();
    }
}
