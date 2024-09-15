package com.project.paradoxplatformer.model.inputmodel;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;

/**
 * Factory interface for creating different input models for
 * {@link ControllableObject}.
 * <p>
 * The {@code InputMovesFactory} provides methods to generate various input
 * models that map
 * different input types to commands for controlling a
 * {@link ControllableObject}. These models
 * can be used to customize the control schemes for different gameplay
 * scenarios.
 * </p>
 */
public interface InputMovesFactory {

    /**
     * Creates a standard input model for controlling a {@link ControllableObject}.
     * <p>
     * This model typically maps common input types (e.g., arrow keys) to standard
     * commands
     * (e.g., move left, move right) for typical gameplay controls.
     * </p>
     *
     * @return an {@link InputModel} configured with standard controls
     */
    InputModel<ControllableObject> standardModel();

    /**
     * Creates an input model using the WASD keys for controlling a
     * {@link ControllableObject}.
     * <p>
     * This model maps the WASD keys to commands, allowing for movement and actions
     * using
     * the WASD keys, which is a common control scheme in many games.
     * </p>
     *
     * @return an {@link InputModel} configured with WASD controls
     */
    InputModel<ControllableObject> wasdModel();

    /**
     * Creates an advanced input model for controlling a {@link ControllableObject}.
     * <p>
     * This model may include additional or custom controls beyond the standard and
     * WASD models,
     * providing a more complex control scheme for advanced gameplay.
     * </p>
     *
     * @return an {@link InputModel} configured with advanced controls
     */
    InputModel<ControllableObject> advancedModel();

    /**
     * Creates an inverted version of an existing input model.
     * <p>
     * This method generates a new {@link InputModel} by inverting the controls of
     * the provided
     * model. For example, it may swap the functions of certain keys to provide an
     * alternative
     * control scheme.
     * </p>
     *
     * @param model the original {@link InputModel} to be inverted
     * @return an {@link InputModel} configured with inverted controls
     */
    InputModel<ControllableObject> invertedModel(InputModel<ControllableObject> model);

}
