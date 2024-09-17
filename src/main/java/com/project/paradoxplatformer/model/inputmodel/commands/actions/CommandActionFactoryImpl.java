package com.project.paradoxplatformer.model.inputmodel.commands.actions;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;

/**
 * An implementation of {@link CommandActionFactory} that provides concrete
 * commands
 * for controlling a {@link ControllableObject}.
 * <p>
 * This class implements the factory methods to produce commands that can be
 * used to
 * perform actions on a {@code ControllableObject}, such as moving left,
 * jumping, or moving right.
 * </p>
 */
public final class CommandActionFactoryImpl implements CommandActionFactory {

    /**
     * Constructs a new {@code CommandActionFactoryImpl}.
     */
    public CommandActionFactoryImpl() {
    }

    /**
     * Creates a command to move the {@link ControllableObject} to the left.
     * 
     * @return a {@link Command} that moves the {@code ControllableObject} to the
     *         left
     */
    @Override
    public Command<ControllableObject> leftCommand() {
        return ControllableObject::moveLeft;
    }

    /**
     * Creates a command to make the {@link ControllableObject} jump.
     * 
     * @return a {@link Command} that makes the {@code ControllableObject} jump
     */
    @Override
    public Command<ControllableObject> upCommand() {
        return ControllableObject::jump;
    }

    /**
     * Creates a command to move the {@link ControllableObject} to the right.
     * 
     * @return a {@link Command} that moves the {@code ControllableObject} to the
     *         right
     */
    @Override
    public Command<ControllableObject> rightCommand() {
        return ControllableObject::moveRight;
    }
}
