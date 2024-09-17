package com.project.paradoxplatformer.model.inputmodel.commands.actions;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;

/**
 * A factory interface for creating commands for controlling a
 * {@link ControllableObject}.
 * <p>
 * This interface provides methods to create commands for various actions that a
 * {@code ControllableObject} can perform, such as moving left, moving up, or
 * moving right.
 * </p>
 */
public interface CommandActionFactory {

    /**
     * Creates a command for moving the {@link ControllableObject} to the left.
     * 
     * @return a {@link Command} that moves the {@code ControllableObject} to the
     *         left
     */
    Command<ControllableObject> leftCommand();

    /**
     * Creates a command for moving the {@link ControllableObject} upwards.
     * 
     * @return a {@link Command} that moves the {@code ControllableObject} upwards
     */
    Command<ControllableObject> upCommand();

    /**
     * Creates a command for moving the {@link ControllableObject} to the right.
     * 
     * @return a {@link Command} that moves the {@code ControllableObject} to the
     *         right
     */
    Command<ControllableObject> rightCommand();
}
