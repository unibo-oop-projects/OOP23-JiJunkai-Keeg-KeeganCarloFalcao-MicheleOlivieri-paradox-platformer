package com.project.paradoxplatformer.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.paradoxplatformer.controller.event.EventManager;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the EventManager class, ensuring functionality for
 * subscribing, publishing, unsubscribing events, and verifying the Singleton
 * pattern.
 */
class EventManagerTest {

    private static final int TEST_PARAM = 123;
    private static final String TEST_EVENT_TYPE = "testEvent";
    private EventManager<String, Integer> eventManager;

    private List<String> capturedEvents;

    /**
     * Sets up the EventManager instance before each test.
     */
    @BeforeEach
    void setUp() {
        eventManager = EventManager.getInstance();
        capturedEvents = new ArrayList<>();
    }

    /**
     * Tests the subscribe and publish functionality of the EventManager.
     * Subscribes to an event and verifies that the correct parameters are captured
     * when the event is published.
     */
    @Test
    @SuppressFBWarnings(value = "UwF", justification = "Fields are initialized in @BeforeEach method before usage.")
    void testSubscribeAndPublish() {
        // Setup
        final BiConsumer<Integer, String> action = (param1, param2) -> capturedEvents
                .add("Event triggered with params: " + param1 + ", " + param2);

        // Subscribe to event
        eventManager.subscribe(TEST_EVENT_TYPE, action);

        // Publish the event
        eventManager.publish(TEST_EVENT_TYPE, TEST_PARAM, "Hello");

        // Verify
        assertTrue(capturedEvents.contains("Event triggered with params: 123, Hello"),
                "The published event should capture the correct parameters.");
    }

    /**
     * Tests the unsubscribe functionality of the EventManager.
     * Ensures that after unsubscribing, the event action is not triggered.
     */
    @Test
    @SuppressFBWarnings(value = "UwF", justification = "Fields are initialized in @BeforeEach method before usage.")
    void testUnsubscribe() {
        // Setup
        final BiConsumer<Integer, String> action = (param1, param2) -> capturedEvents
                .add("Event triggered with params: " + param1 + ", " + param2);

        // Subscribe to event
        eventManager.subscribe(TEST_EVENT_TYPE, action);

        // Unsubscribe from event
        eventManager.unsubscribe(TEST_EVENT_TYPE);

        // Publish the event
        eventManager.publish(TEST_EVENT_TYPE, TEST_PARAM, "Hello");

        // Verify
        assertFalse(capturedEvents.contains("Event triggered with params: 123, Hello"),
                "Unsubscribed event should not trigger the action.");
    }

    /**
     * Tests the Singleton pattern of the EventManager.
     * Verifies that multiple calls to getInstance return the same instance.
     */
    @Test
    void testSingletonInstance() {
        // Get two instances
        final EventManager<String, Integer> instance1 = EventManager.getInstance();
        final EventManager<String, Integer> instance2 = EventManager.getInstance();

        // Verify both instances are the same
        assertSame(instance1, instance2, "EventManager should implement the Singleton pattern.");
    }
}
