package com.project.paradoxplatformer.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public class EventManager {

    private static EventManager instance;
    private final Map<EventType, Object> eventMap = new HashMap<>();

    private EventManager() {
    }

    public static EventManager getInstance() {
        if (instance == null) {
            synchronized (EventManager.class) {
                if (instance == null) {
                    instance = new EventManager();
                }
            }
        }
        return instance;
    }

    // Method to subscribe to events with specific parameter types
    public <T, U> void subscribe(EventType eventName, BiConsumer<T, U> action) {
        eventMap.put(eventName, action);
    }

    // Method to publish events with specific parameter types
    @SuppressWarnings("unchecked")
    public <T, U> void publish(EventType eventName, T param1, U param2) {
        Optional.of(eventName)
                .filter(eventMap::containsKey)
                .map(eventMap::get)
                .map(action -> (BiConsumer<T, U>) action)
                .ifPresentOrElse(
                        action -> action.accept(param1, param2),
                        () -> System.out.println("Could not find event " + eventName + ", event not published"));
    }

    public void unsubscribe(final EventType eventName) {
        this.eventMap.remove(eventName);
    }
}
