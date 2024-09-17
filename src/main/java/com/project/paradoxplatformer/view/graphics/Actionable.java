package com.project.paradoxplatformer.view.graphics;

import com.project.paradoxplatformer.model.inputmodel.commands.Command;

/**
 * Represents an entity that can respond to actions performed by an actor.
 * 
 * @param <G> the type of the actor performing the action
 */
public interface Actionable {

    /**
     * Executes the specified action with the given actor.
     * 
     * @param <G>    the type of the actor performing the action
     * @param action the command to be executed
     * @param actor  the actor performing the action
     */
    <G> void onAction(Command<G> action, G actor);
}
