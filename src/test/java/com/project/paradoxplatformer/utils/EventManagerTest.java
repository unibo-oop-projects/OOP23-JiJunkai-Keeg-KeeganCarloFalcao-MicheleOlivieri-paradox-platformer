package com.project.paradoxplatformer.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the EventManager class, ensuring functionality for
 * subscribing,
 * publishing, unsubscribing events, and verifying the Singleton pattern.
 */
class EventManagerTest {

    private static final int TEST_PARAM = 123;
    private static final String TEST_EVENT_TYPE = "testEvent";
    private EventManager<String, Integer> eventManager;

    /**
     * Sets up the EventManager instance before each test.
     */
    @BeforeEach
    void setUp() {
        eventManager = EventManager.getInstance();
    }

    /**
     * Tests the subscribe and publish functionality of the EventManager.
     * Subscribes to an event and verifies that the correct parameters are printed
     * when the event is published.
     */
    @Test
    void testSubscribeAndPublish() {
        // Setup
        BiConsumer<Integer, String> action = (param1, param2) -> System.out
                .println("Event triggered with params: " + param1 + ", " + param2);

        // Subscribe to event
        eventManager.subscribe(TEST_EVENT_TYPE, action);

        // Create output container
        final StringBuilder output = new StringBuilder();

        // Override System.out to capture the print statements
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(final int b) {
                output.append((char) b);
            }
        }));

        // Publish the event
        eventManager.publish(TEST_EVENT_TYPE, TEST_PARAM, "Hello");

        // Verify
        assertTrue(output.toString().contains("Event triggered with params: 123, Hello"),
                "The published event should print the correct parameters.");
    }

    /**
     * Tests the unsubscribe functionality of the EventManager.
     * Ensures that after unsubscribing, the event action is not triggered.
     */
    @Test
    void testUnsubscribe() {
        // Setup
        BiConsumer<Integer, String> action = (param1, param2) -> {
            throw new IllegalStateException("This should not be called");
        };

        // Subscribe to event
        eventManager.subscribe(TEST_EVENT_TYPE, action);

        // Unsubscribe from event
        eventManager.unsubscribe(TEST_EVENT_TYPE);

        // Create output container
        final StringBuilder output = new StringBuilder();

        // Override System.out to capture the print statements
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(final int b) {
                output.append((char) b);
            }
        }));

        // Publish the event
        eventManager.publish(TEST_EVENT_TYPE, TEST_PARAM, "Hello");

        // Verify
        assertTrue(output.toString().contains("Could not find event testEvent, event not published"),
                "Unsubscribed event should not trigger the action and should print the appropriate message.");
    }

    /**
     * Tests the Singleton pattern of the EventManager.
     * Verifies that multiple calls to getInstance return the same instance.
     */
    @Test
    void testSingletonInstance() {
        // Get two instances
        EventManager<String, Integer> instance1 = EventManager.getInstance();
        EventManager<String, Integer> instance2 = EventManager.getInstance();

        // Verify both instances are the same
        assertSame(instance1, instance2, "EventManager should implement the Singleton pattern.");
    }
}
