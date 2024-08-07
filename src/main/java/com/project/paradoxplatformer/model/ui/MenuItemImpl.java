package com.project.paradoxplatformer.model.ui;

import com.project.paradoxplatformer.model.ui.api.MenuItem;

public class MenuItemImpl implements MenuItem {
    private final String name;
    private final Runnable action;

    public MenuItemImpl(String name, Runnable action) {
        this.name = name;
        this.action = action;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        action.run();
    }
}