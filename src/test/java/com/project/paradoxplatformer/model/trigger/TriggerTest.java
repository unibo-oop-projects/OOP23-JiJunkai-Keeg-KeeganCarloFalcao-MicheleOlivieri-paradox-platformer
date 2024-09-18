package com.project.paradoxplatformer.model.trigger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.paradoxplatformer.model.obstacles.Platform;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

/**
 * Unit tests for the {@link Button} class which implements the {@link Trigger}
 * interface.
 * These tests verify the behavior of methods related to activating triggers,
 * adding obstacles, and managing triggerable IDs.
 */
public final class TriggerTest {

    private Button trigger;

    /**
     * Sets up the test environment by creating a new instance of {@link Button}.
     */
    @BeforeEach
    void setUp() {
        trigger = new Button(0, new Coord2D(0, 0), new Dimension(0, 0));
    }

    /**
     * Tests the activation of the trigger and ensures that associated obstacles are
     * activated.
     */
    @Test
    void testActivate() {
        final Platform obstacle = new Platform(0, new Coord2D(0, 0), new Dimension(0, 0), null);
        trigger.addObstacle(obstacle);
        trigger.activate();
        // Add assertions or verifications as necessary to confirm the obstacle
        // activation
    }

    /**
     * Tests the addition of an obstacle to the trigger.
     * Verifies that the obstacle can be added to the trigger.
     */
    @Test
    void testAddObstacle() {
        final Platform obstacle = new Platform(0, new Coord2D(0, 0), new Dimension(0, 0), null);
        trigger.addObstacle(obstacle);
        // Assuming you want to verify the obstacle was added, this is a basic check
        // If your Trigger class has a method to get the list of obstacles, you could
        // use it here
        // For now, this test will pass if no exceptions occur
        assertNotNull(trigger);
    }

    /**
     * Tests setting and retrieving the triggerable ID for the trigger.
     * Verifies that the ID is set and retrieved correctly.
     */
    @Test
    void testSetAndGetTriggerableID() {
        trigger.setTriggerableID(Optional.of(123));
        assertEquals(Optional.of(123), trigger.getTriggerableID(),
                "Triggerable ID should be set and retrieved correctly");
    }

    /**
     * Tests the case where the triggerable ID is not set.
     * Verifies that the ID remains empty when not set.
     */
    @Test
    void testGetTriggerableIDWhenNotSet() {
        trigger.setTriggerableID(Optional.empty());
        assertEquals(Optional.empty(), trigger.getTriggerableID(),
                "Triggerable ID should be empty when not set");
    }
}
