package com.project.paradoxplatformer.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.project.paradoxplatformer.utils.effect.ViewEventType;
import com.project.paradoxplatformer.utils.effect.api.Level;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

public class EventManager {

    private static EventManager instance;
    private final Map<ViewEventType, BiConsumerWithAndThen<PageIdentifier, Level>> eventMap = new HashMap<>();

    private EventManager() {
    }

    // Singleton pattern to ensure only one instance
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

    // Method to subscribe to events with two parameters
    public void subscribe(ViewEventType viewEventType, BiConsumerWithAndThen<PageIdentifier, Level> action) {
        eventMap.put(viewEventType, action);
    }

    // Method to publish events with two parameters
    public void publish(ViewEventType viewEventType, PageIdentifier pageIdentifier, Level param) {
        Optional.of(viewEventType)
                .filter(eventMap::containsKey)
                .map(eventMap::get)
                .ifPresentOrElse(
                        f -> f.accept(pageIdentifier, param),
                        () -> System.out.println("Could not find event " + viewEventType + ", event not published"));
    }

    public void unsuscribe(final ViewEventType viewEventType) {
        this.eventMap.remove(viewEventType);
    }
}
