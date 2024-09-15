package com.project.paradoxplatformer.model.inputmodel;

import java.util.Map;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;

import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;
import com.project.paradoxplatformer.model.inputmodel.commands.actions.CommandActionFactory;
import com.project.paradoxplatformer.model.inputmodel.commands.actions.CommandActionFactoryImpl;

/**
 * Implementation of the {@link InputMovesFactory} interface for creating
 * various input models.
 * <p>
 * The {@code InputMovesFactoryImpl} provides methods to create different input
 * models for
 * controlling {@link ControllableObject}. It includes standard, WASD, and
 * advanced models,
 * as well as an inverted version of any provided model.
 * </p>
 */
public final class InputMovesFactoryImpl implements InputMovesFactory {

    private final CommandActionFactory cmdFactory;
    private final Map<Command<ControllableObject>, Command<ControllableObject>> oppositeMap;

    /**
     * Constructs an {@code InputMovesFactoryImpl} instance, initializing command
     * factories
     * and setting up the opposite commands map.
     */
    public InputMovesFactoryImpl() {
        this.cmdFactory = new CommandActionFactoryImpl();
        this.oppositeMap = Collections.unmodifiableMap(new HashMap<>(Map.of(
                cmdFactory.leftCommand(), cmdFactory.rightCommand(),
                cmdFactory.rightCommand(), cmdFactory.leftCommand())));
    }

    /**
     * Creates a standard input model for controlling {@link ControllableObject}.
     * <p>
     * This model maps the default input types (LEFT, RIGHT, UP) to commands using
     * the
     * standard control scheme.
     * </p>
     *
     * @return an {@link InputModel} configured with standard controls
     */
    @Override
    public InputModel<ControllableObject> standardModel() {
        return () -> Collections.unmodifiableMap(new EnumMap<>(Map.of(
                InputType.LEFT, cmdFactory.leftCommand(),
                InputType.RIGHT, cmdFactory.rightCommand(),
                InputType.UP, cmdFactory.upCommand())));
    }

    /**
     * Creates an input model using the WASD keys for controlling
     * {@link ControllableObject}.
     * <p>
     * This model maps the WASD keys to commands, providing an alternative control
     * scheme.
     * </p>
     *
     * @return an {@link InputModel} configured with WASD controls
     */
    @Override
    public InputModel<ControllableObject> wasdModel() {
        return () -> Collections.unmodifiableMap(new EnumMap<>(Map.of(
                InputType.A, cmdFactory.leftCommand(),
                InputType.D, cmdFactory.rightCommand(),
                InputType.W, cmdFactory.upCommand())));
    }

    /**
     * Creates an inverted version of an existing input model.
     * <p>
     * This method generates a new {@link InputModel} by swapping the commands for
     * input types
     * in the provided model, effectively inverting the control scheme.
     * </p>
     *
     * @param ip the original {@link InputModel} to be inverted
     * @return an {@link InputModel} configured with inverted controls
     */
    @Override
    public InputModel<ControllableObject> invertedModel(final InputModel<ControllableObject> ip) {
        return () -> {
            var inverted = new EnumMap<>(ip.getModel());
            inverted.entrySet().stream()
                    .filter(e -> this.oppositeMap.containsKey(e.getValue()))
                    .forEach(this::invert);
            return inverted;
        };
    }

    /**
     * Creates an advanced input model that combines the WASD model with the
     * standard model.
     * <p>
     * This model extends the WASD controls by incorporating additional controls
     * from the
     * standard model, providing a more comprehensive control scheme.
     * </p>
     *
     * @return an {@link InputModel} configured with advanced controls
     */
    @Override
    public InputModel<ControllableObject> advancedModel() {
        return new StdModelDecorator(wasdModel());
    }

    /**
     * Decorator class for enhancing the WASD input model with additional controls
     * from the
     * standard model.
     */
    private final class StdModelDecorator implements InputModel<ControllableObject> {
        private final InputModel<ControllableObject> toDecorate;
        private final Map<InputType, Command<ControllableObject>> modifMapModel;

        /**
         * Constructs a {@code StdModelDecorator} instance.
         * 
         * @param toDecorate the {@link InputModel} to decorate
         */
        private StdModelDecorator(final InputModel<ControllableObject> toDecorate) {
            this.toDecorate = toDecorate;
            this.modifMapModel = new HashMap<>(this.toDecorate.getModel());
            this.modifMapModel.putAll(InputMovesFactoryImpl.this.standardModel().getModel());
        }

        /**
         * Returns the decorated input model with combined controls.
         * 
         * @return an unmodifiable map of input types to commands
         */
        @Override
        public Map<InputType, Command<ControllableObject>> getModel() {
            return Collections.unmodifiableMap(this.modifMapModel);
        }
    }

    /**
     * Inverts the command for a given map entry.
     * 
     * @param e the map entry whose command needs to be inverted
     */
    private void invert(final Map.Entry<InputType, Command<ControllableObject>> e) {
        e.setValue(this.oppositeMap.get(e.getValue()));
    }
}
