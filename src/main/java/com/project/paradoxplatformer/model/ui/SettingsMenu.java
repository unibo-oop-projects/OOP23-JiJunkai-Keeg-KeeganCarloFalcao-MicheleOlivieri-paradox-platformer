package com.project.paradoxplatformer.model.ui;

import java.util.List;

import com.project.paradoxplatformer.model.ui.api.Menu;
import com.project.paradoxplatformer.model.ui.api.MenuItem;

public class SettingsMenu implements Menu {
    private final List<MenuItem> settings;

    public SettingsMenu(List<MenuItem> settings) {
        this.settings = settings;
    }

    @Override
    public void display() {
        System.out.println("Settings Menu:");
        for (int i = 0; i < settings.size(); i++) {
            System.out.println((i + 1) + ". " + settings.get(i).getName());
        }
    }

    @Override
    public void handleInput(String input) {
        int choice = Integer.parseInt(input);
        if (choice > 0 && choice <= settings.size()) {
            settings.get(choice - 1).execute();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}
