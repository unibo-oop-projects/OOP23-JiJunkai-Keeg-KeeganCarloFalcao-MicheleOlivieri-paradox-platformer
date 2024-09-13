package com.project.paradoxplatformer.view.graphics;

import com.project.paradoxplatformer.model.inputmodel.commands.Command;

public interface Actionable {
    <G> void onAction(Command<G> action, G actor);
}
