package com.project.paradoxplatformer.model.ui.impl;

import com.project.paradoxplatformer.model.ui.api.MenuItem;

public abstract class AbstractMenuItem implements MenuItem {

    private String name;

    public AbstractMenuItem(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
