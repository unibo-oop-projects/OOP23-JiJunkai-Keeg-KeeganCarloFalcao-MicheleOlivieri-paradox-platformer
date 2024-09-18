package com.project.paradoxplatformer.controller.input;

import java.util.Optional;

import com.project.paradoxplatformer.controller.input.api.KeyAssetter;
import com.project.paradoxplatformer.model.inputmodel.InputModel;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;

/**
 * Manages and processes input commands for a given actor based on input models and key assets.
 * <p>
 * The {@code InputController} utilizes an {@code InputModel} to map and execute commands
 * that correspond to key inputs defined by a {@code KeyAssetter}. This allows for flexible
 * and dynamic command execution based on user inputs or other triggering events.
 * </p>
 * 
 * @param <T> the type of the actor that the commands will be executed upon. This actor
 *            can be any object that supports command execution as defined by the
 *            {@code Command} interface.
 */
public final class InputController<T> {

    private final InputModel<T> inModel;

    /**
     * Constructs a new {@code InputController} with the specified input model.
     * <p>
     * The {@code InputModel} is used to map key inputs to commands that will be executed
     * on the actor. This model provides a way to define which commands are associated
     * with which key inputs, facilitating the handling of user or system commands.
     * </p>
     * 
     * @param inModel the input model used to map keys to commands. This model should
     *                contain mappings from keys to commands that the {@code InputController}
     *                will use to execute the corresponding actions on the actor.
     */
    public InputController(final InputModel<T> inModel) {
        this.inModel = inModel;
    }

    /**
     * Processes the key assets and executes the corresponding commands on the specified actor.
     * <p>
     * This method checks the pool of key assets provided by the {@code KeyAssetter}. If the pool
     * is not empty, it retrieves and executes all commands mapped to the keys present in the
     * input model. Each command in the pool is executed sequentially on the actor. If the pool
     * is empty, the provided idle command is executed instead. This ensures that the actor
     * always receives a command to execute, even when no keys are pressed or available.
     * </p>
     * 
     * @param <K>       the type of key used by the key assetter. This type defines the keys
     *                  that are used to look up commands in the input model.
     * @param keyAssets the key assetter that provides the pool of key assets. This pool
     *                  contains optional key values that may be associated with commands
     *                  in the input model.
     * @param actor     the actor upon which the commands are executed. This is the object
     *                  that will perform the actions defined by the commands.
     * @param onIdle    the command to execute if the key pool is empty. This command is
     *                  executed when no keys are pressed or when the pool does not contain
     *                  any keys with associated commands.
     */
    public <K> void checkPool(final KeyAssetter<K> keyAssets, final T actor, final Command<T> onIdle) {
        if (!keyAssets.getUnmodifiablePool().isEmpty()) {
            keyAssets.getUnmodifiablePool().stream()
                    .filter(Optional::isPresent) // Filter out empty optionals
                    .map(Optional::get) // Retrieve the key from the optional
                    .filter(inModel.getModel()::containsKey) // Ensure the key exists in the input model
                    .map(inModel.getModel()::get) // Retrieve the command associated with the key
                    .forEach(c -> c.execute(actor)); // Execute each command on the actor
        } else {
            onIdle.execute(actor); // Execute the idle command if no keys are present
        }
    }
}

