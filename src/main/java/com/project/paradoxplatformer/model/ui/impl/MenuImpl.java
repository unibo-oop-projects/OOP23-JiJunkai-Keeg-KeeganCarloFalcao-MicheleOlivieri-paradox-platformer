package com.project.paradoxplatformer.model.ui.impl;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

import com.project.paradoxplatformer.model.ui.api.Menu;
import com.project.paradoxplatformer.model.ui.api.MenuItem;

public class MenuImpl implements Menu {

    private final String title;
    private final List<MenuItem> items;

    public MenuImpl(String title, List<MenuItem> items) {
        this.title = title;
        this.items = Collections.unmodifiableList(new ArrayList<>(items)); // Defensive copy
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public List<MenuItem> getItems() {
        return this.items;
    }

}
