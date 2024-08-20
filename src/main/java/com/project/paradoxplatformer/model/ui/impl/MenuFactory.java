package com.project.paradoxplatformer.model.ui.impl;

import com.project.paradoxplatformer.model.ui.api.Menu;
import com.project.paradoxplatformer.model.ui.api.MenuStrategy;

public class MenuFactory {

    public static Menu getMenu(MenuStrategy strategy) {
        return strategy.createMenu();
    }
}
