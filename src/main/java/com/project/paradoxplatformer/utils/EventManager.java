package com.project.paradoxplatformer.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * EventManager provides a mechanism for communication between different classes
 * without passing instances between them. It follows the Singleton pattern to
 * ensure
 * that only one instance manages all events across the application.
 *
 * @param <T> The type of event identifiers.
 * @param <U> The type of the first parameter for event handlers.
 */
public class EventManager<T, U> {

    private static EventManager<?, ?> instance; // Singleton instance of the EventManager
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
    @SuppressWarnings("unchecked")
    public static <T, U> EventManager<T, U> getInstance() {
        if (instance == null) {
            synchronized (EventManager.class) {
                if (instance == null) {
                    instance = new EventManager<>();
                }
            }
        }
        return (EventManager<T, U>) instance;
    }

    /**
     * Subscribes an event handler to a specific event type.
     *
     * @param eventType The type of event to subscribe to.
     * @param action    The action to perform when the event is published.
     * @param <V>       The type of the second parameter for event handlers.
     */
    public <V> void subscribe(T eventType, BiConsumer<U, V> action) {
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
    @SuppressWarnings("unchecked")
    public <V> void publish(T eventType, U param1, V param2) {
        Optional.of(eventType)
                .filter(eventMap::containsKey)
                .map(eventMap::get)
                .ifPresentOrElse(
                        f -> {
                            System.out.println(""); // Placeholder for additional debugging or logging
                            ((BiConsumer<U, V>) f).accept(param1, param2);
                        },
                        () -> System.out.println("Could not find event " + eventType + ", event not published"));
    }

    /**
     * Unsubscribes from an event type, removing the associated event handler.
     *
     * @param eventType The type of event to unsubscribe from.
     */
    public void unsubscribe(final T eventType) {
        this.eventMap.remove(eventType);
    }
}
