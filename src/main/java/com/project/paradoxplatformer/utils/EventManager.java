package com.project.paradoxplatformer.utils;

import java.util.*;
import java.util.function.BiConsumer;

public class EventManager {

    private static EventManager instance;
    private final Map<EventType, List<BiConsumer<?, ?>>> eventMap = new HashMap<>();

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

    // Subscribe to an event with specific parameter types
    public <T, U> void subscribe(EventType eventName, BiConsumer<T, U> action) {
        System.out.println("A new subscriptions!! from :" + action);
        eventMap.computeIfAbsent(eventName, k -> new ArrayList<>()).add(action);
    }

    // Publish an event with specific parameter types, using a copied list to ensure
    // safe iteration
    @SuppressWarnings("unchecked")
    public <T, U> void publish(EventType eventName, T param1, U param2) {
        System.out.println("Published!!!");
        List<BiConsumer<?, ?>> actions = eventMap.get(eventName);
        if (actions != null) {
            List<BiConsumer<?, ?>> actionsCopy = new ArrayList<>(actions); // Copy to avoid concurrent modification
            Collections.reverse(actionsCopy);
            System.out.println("There are " + actionsCopy.size() + " to complete.");
            for (BiConsumer<?, ?> action : actionsCopy) {
                ((BiConsumer<T, U>) action).accept(param1, param2);
            }
        } else {
            System.out.println("No event found for " + eventName);
        }
    }

    public <T, U> void unsubscribe(EventType eventName, BiConsumer<T, U> action) {
        List<BiConsumer<?, ?>> actions = eventMap.get(eventName);
        if (actions != null) {
            actions.remove(action);
        }
    }
}
