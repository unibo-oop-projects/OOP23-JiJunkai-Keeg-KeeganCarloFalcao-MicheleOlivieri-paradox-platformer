package com.project.paradoxplatformer.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

class EventManagerTest {

    private EventManager<String, Integer> eventManager;

    @BeforeEach
    void setUp() {
        eventManager = EventManager.getInstance();
    }

    @Test
    void testSubscribeAndPublish() {
        // Setup
        String eventType = "testEvent";
        BiConsumer<Integer, String> action = (param1, param2) -> {
            System.out.println("Event triggered with params: " + param1 + ", " + param2);
        };

        // Subscribe to event
        eventManager.subscribe(eventType, action);

        // Create output container
        final StringBuilder output = new StringBuilder();

        // Override System.out to capture the print statements
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                output.append((char) b);
            }
        }));

        // Publish the event
        eventManager.publish(eventType, 123, "Hello");

        // Verify
        assertTrue(output.toString().contains("Event triggered with params: 123, Hello"),
                "The published event should print the correct parameters.");
    }

    @Test
    void testUnsubscribe() {
        // Setup
        String eventType = "testEvent";
        BiConsumer<Integer, String> action = (param1, param2) -> {
            throw new IllegalStateException("This should not be called");
        };

        // Subscribe to event
        eventManager.subscribe(eventType, action);

        // Unsubscribe from event
        eventManager.unsubscribe(eventType);

        // Create output container
        final StringBuilder output = new StringBuilder();

        // Override System.out to capture the print statements
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                output.append((char) b);
            }
        }));

        // Publish the event
        eventManager.publish(eventType, 123, "Hello");

        // Verify
        assertTrue(output.toString().contains("Could not find event testEvent, event not published"),
                "Unsubscribed event should not trigger the action and print the appropriate message.");
    }

    @Test
    void testSingletonInstance() {
        // Get two instances
        EventManager<String, Integer> instance1 = EventManager.getInstance();
        EventManager<String, Integer> instance2 = EventManager.getInstance();

        // Verify both instances are the same
        assertSame(instance1, instance2, "EventManager should implement Singleton pattern.");
    }
}
