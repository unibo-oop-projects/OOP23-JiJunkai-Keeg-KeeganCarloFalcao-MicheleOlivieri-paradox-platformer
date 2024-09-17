package com.project.paradoxplatformer.model;

import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;

public record MenuItem(String name, Command<GameController<?>> action) {

}
