package com.project.paradoxplatformer.utils.commands.actions;

import com.project.paradoxplatformer.utils.commands.Command;

public interface CommandActionFactory {
    
    Command leftCommand();

    Command upCommand();

    Command rightCommand();
}
