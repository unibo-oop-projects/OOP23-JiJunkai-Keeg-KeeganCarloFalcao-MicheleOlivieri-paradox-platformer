package com.project.paradoxplatformer.model.innersetting;

import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;

/**
 * Represents a menu item with a name and an associated action.
 * <p>
 * This record holds the name of the menu item and the command to be executed
 * when the item is selected. The action is represented by a {@link Command}
 * which operates on a {@link GameController}.
 * </p>
 *
 * @param name   the name of the menu item
 * @param action the command to be executed when the menu item is selected
 */
public record MenuItem(String name, Command<GameController<?>> action) {

}
