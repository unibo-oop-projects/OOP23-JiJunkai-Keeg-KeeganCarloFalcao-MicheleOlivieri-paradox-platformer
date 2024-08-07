package com.project.paradoxplatformer.utils.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import com.project.paradoxplatformer.model.ui.ChooseLevelMenu;
import com.project.paradoxplatformer.model.ui.GameScoreboard;
import com.project.paradoxplatformer.model.ui.MenuItemImpl;
import com.project.paradoxplatformer.model.ui.SettingsMenu;
import com.project.paradoxplatformer.model.ui.api.MenuItem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class GameUITest {
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testChooseLevelMenu() {
        MenuItem level1 = new MenuItemImpl("Level 1", () -> System.out.println("Level 1 started"));
        MenuItem level2 = new MenuItemImpl("Level 2", () -> System.out.println("Level 2 started"));
        ChooseLevelMenu menu = new ChooseLevelMenu(List.of(level1, level2));

        menu.display();
        menu.handleInput("1");

        String output = outputStream.toString();
        Assertions.assertTrue(output.contains("Choose Level:"));
        Assertions.assertTrue(output.contains("1. Level 1"));
        Assertions.assertTrue(output.contains("2. Level 2"));
        Assertions.assertTrue(output.contains("Level 1 started"));
    }

    @Test
    public void testSettingsMenu() {
        MenuItem pause = new MenuItemImpl("Pause", () -> System.out.println("Game Paused"));
        MenuItem quit = new MenuItemImpl("Quit", () -> System.exit(0));
        SettingsMenu menu = new SettingsMenu(List.of(pause, quit));

        menu.display();
        menu.handleInput("1");

        String output = outputStream.toString();
        Assertions.assertTrue(output.contains("Settings Menu:"));
        Assertions.assertTrue(output.contains("1. Pause"));
        Assertions.assertTrue(output.contains("2. Quit"));
        Assertions.assertTrue(output.contains("Game Paused"));
    }

    @Test
    public void testScoreboard() {
        GameScoreboard scoreboard = new GameScoreboard();
        scoreboard.updateTimePassed(120);
        scoreboard.incrementDeathCounter();
        scoreboard.incrementDeathCounter(); // Increase death count to test multiple increments

        scoreboard.display();
        String output = outputStream.toString();
        Assertions.assertTrue(output.contains("Time Passed: 120 seconds"));
        Assertions.assertTrue(output.contains("Deaths: 2"));
    }
}
