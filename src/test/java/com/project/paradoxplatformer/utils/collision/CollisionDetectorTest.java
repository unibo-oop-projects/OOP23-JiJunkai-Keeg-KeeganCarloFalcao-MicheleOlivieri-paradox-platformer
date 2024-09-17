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

class CollisionDetectorTest {

    private Button button1;
    private Button button2;
    private Button button3;

    @BeforeEach
    void setUp() {
        // Initialize buttons with different positions and dimensions
        button1 = new Button(0, new Coord2D(100, 200), new Dimension(50, 50));
        button2 = new Button(1, new Coord2D(120, 220), new Dimension(50, 50)); // Overlapping with button1
        button3 = new Button(2, new Coord2D(300, 400), new Dimension(50, 50)); // Not overlapping
    }

    @Test
    void testIsColliding_WhenOverlapping() {
        // Test collision between button1 and button2
        assertTrue(CollisionDetector.isColliding(button1, button2), "Button1 and Button2 should be colliding.");
    }

    @Test
    void testIsColliding_WhenNotOverlapping() {
        // Test no collision between button1 and button3
        assertFalse(CollisionDetector.isColliding(button1, button3), "Button1 and Button3 should not be colliding.");
    }

    @Test
    void testHasCollision_WhenCollisionExists() {
        // List of objects where button1 should collide with button2
        List<Button> buttons = new ArrayList<>();
        buttons.add(button2);
        buttons.add(button3);

        // Test if button1 has a collision in the list
        assertTrue(CollisionDetector.hasCollision(button1, buttons), "Button1 should collide with Button2.");
    }

    @Test
    void testHasCollision_WhenNoCollisionExists() {
        // List of objects where button1 does not collide with any other button
        List<Button> buttons = new ArrayList<>();
        buttons.add(button3);

        // Test if button1 has no collisions
        assertFalse(CollisionDetector.hasCollision(button1, buttons), "Button1 should not collide with Button3.");
    }
}
