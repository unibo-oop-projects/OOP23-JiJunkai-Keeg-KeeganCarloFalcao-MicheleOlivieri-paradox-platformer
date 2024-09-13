package com.project.paradoxplatformer.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public class EventManager<T, U> {

    private static EventManager<?, ?> instance;
    private final Map<T, BiConsumer<U, ?>> eventMap = new HashMap<>();

    private EventManager() {
    }

    // Singleton pattern to ensure only one instance
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

    // Method to subscribe to events with two parameters
    public <V> void subscribe(T eventType, BiConsumer<U, V> action) {
        eventMap.put(eventType, action);
    }

    // Method to publish events with two parameters
    @SuppressWarnings("unchecked")
    public <V> void publish(T eventType, U param1, V param2) {
        Optional.of(eventType)
                .filter(eventMap::containsKey)
                .map(eventMap::get)
                .ifPresentOrElse(
                        f -> {
                            System.out.println("");
                            ((BiConsumer<U, V>) f).accept(param1, param2);
                        },
                        () -> System.out.println("Could not find event " + eventType + ", event not published"));
    }

    public void unsubscribe(final T eventType) {
        this.eventMap.remove(eventType);
    }
}
