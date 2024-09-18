package com.project.paradoxplatformer.utils.collision;

import com.project.paradoxplatformer.model.trigger.Button;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for CollisionDetector class.
 * It tests collision detection between buttons in different scenarios.
 */
class CollisionDetectorTest {

    private static final double BUTTON1_X = 100;
    private static final double BUTTON1_Y = 200;
    private static final double BUTTON2_X = 120;
    private static final double BUTTON2_Y = 220;
    private static final double BUTTON3_X = 300;
    private static final double BUTTON3_Y = 400;
    private static final int BUTTON_WIDTH = 50;
    private static final int BUTTON_HEIGHT = 50;

    private Button button1;
    private Button button2;
    private Button button3;

    /**
     * Initializes buttons with different positions and dimensions before each test.
     */
    @BeforeEach
    void setUp() {
        button1 = new Button(0, new Coord2D(BUTTON1_X, BUTTON1_Y), new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button2 = new Button(1, new Coord2D(BUTTON2_X, BUTTON2_Y), new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT)); // Overlapping
                                                                                                                // with
                                                                                                                // button1
        button3 = new Button(2, new Coord2D(BUTTON3_X, BUTTON3_Y), new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT)); // Not
                                                                                                                // overlapping
    }

    /**
     * Tests that isColliding() returns true when two buttons overlap.
     */
    @Test
    void testIsCollidingWhenOverlapping() {
        assertTrue(CollisionDetector.isColliding(button1, button2), "Button1 and Button2 should be colliding.");
    }

    /**
     * Tests that isColliding() returns false when two buttons do not overlap.
     */
    @Test
    void testIsCollidingWhenNotOverlapping() {
        assertFalse(CollisionDetector.isColliding(button1, button3), "Button1 and Button3 should not be colliding.");
    }

    /**
     * Tests that hasCollision() returns true when a collision is detected in a list
     * of objects.
     */
    @Test
    void testHasCollisionWhenCollisionExists() {
        final List<Button> buttons = new ArrayList<>();
        buttons.add(button2); // Colliding button
        buttons.add(button3);

        assertTrue(CollisionDetector.hasCollision(button1, buttons), "Button1 should collide with Button2.");
    }

    /**
     * Tests that hasCollision() returns false when no collisions are detected in a
     * list of objects.
     */
    @Test
    void testHasCollisionWhenNoCollisionExists() {
        final List<Button> buttons = new ArrayList<>();
        buttons.add(button3);

        assertFalse(CollisionDetector.hasCollision(button1, buttons), "Button1 should not collide with Button3.");
    }
}
