package com.project.paradoxplatformer.model.menu;

import java.util.HashMap;
import java.util.Map;

import com.project.paradoxplatformer.model.menu.api.Command;
import com.project.paradoxplatformer.model.menu.api.CommandType;

public class Menu {
    private Map<CommandType, Command> commands = new HashMap<>();

    public void addCommand(CommandType type, Command command) {
        commands.put(type, command);
    }

    public void executeCommand(CommandType type) {
        if (commands.containsKey(type)) {
            commands.get(type).execute();
        } else {
            throw new IllegalArgumentException("Command not found.");
        }
    }
}
