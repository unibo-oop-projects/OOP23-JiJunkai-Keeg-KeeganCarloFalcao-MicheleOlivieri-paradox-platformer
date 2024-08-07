package com.project.paradoxplatformer.model.ui;

import java.util.List;

import com.project.paradoxplatformer.model.ui.api.Menu;
import com.project.paradoxplatformer.model.ui.api.MenuItem;

public class ChooseLevelMenu implements Menu {
    private final List<MenuItem> levels;

    public ChooseLevelMenu(List<MenuItem> levels) {
        this.levels = levels;
    }

    @Override
    public void display() {
        System.out.println("Choose Level:");
        for (int i = 0; i < levels.size(); i++) {
            System.out.println((i + 1) + ". " + levels.get(i).getName());
        }
    }

    @Override
    public void handleInput(String input) {
        int choice = Integer.parseInt(input);
        if (choice > 0 && choice <= levels.size()) {
            levels.get(choice - 1).execute();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}