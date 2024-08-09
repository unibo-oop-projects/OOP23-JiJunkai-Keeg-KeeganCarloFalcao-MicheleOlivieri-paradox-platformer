package com.project.paradoxplatformer.model.ui.impl.strategies;

import com.project.paradoxplatformer.model.ui.api.Menu;
import com.project.paradoxplatformer.model.ui.api.MenuItem;
import com.project.paradoxplatformer.model.ui.api.MenuStrategy;
import com.project.paradoxplatformer.model.ui.impl.MenuImpl;
import com.project.paradoxplatformer.model.ui.impl.NewGameButton;
import com.project.paradoxplatformer.model.ui.impl.QuitGameButton;

import java.util.List;
import java.util.Arrays;

public class MainMenuStrategy implements MenuStrategy {

    @Override
    public Menu createMenu() {
        List<MenuItem> items = Arrays.asList(new NewGameButton(),
                new QuitGameButton());
        return new MenuImpl("Main Menu", items);
    }
}
