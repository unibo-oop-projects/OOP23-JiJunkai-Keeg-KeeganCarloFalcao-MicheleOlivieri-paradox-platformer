package com.project.paradoxplatformer.controller.input;

import java.util.Optional;

import com.project.paradoxplatformer.controller.input.api.KeyAssetter;
import com.project.paradoxplatformer.model.inputmodel.InputModel;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;

/**
 * Handles the processing of input commands for a given actor.
 * <p>
 * The {@code InputController} uses an {@code InputModel} to map and execute
 * commands
 * based on the key assets provided by a {@code KeyAssetter}.
 * </p>
 * 
 * @param <T> the type of the actor for which commands are executed
 */
public final class InputController<T> {

    private final InputModel<T> inModel;

    /**
     * Constructs an {@code InputController} with the specified input model.
     * 
     * @param inModel the input model used to map and retrieve commands
     */
    public InputController(final InputModel<T> inModel) {
        this.inModel = inModel;
    }

    /**
     * Checks the pool of key assets and executes the corresponding commands on the
     * actor.
     * <p>
     * If the pool is not empty, it processes all available commands that are
     * present
     * in the input model and executes them on the given actor. If the pool is
     * empty,
     * the provided idle command is executed on the actor.
     * </p>
     * 
     * @param <K>       the type of key used by the key assetter
     * @param keyAssets the key assetter providing the pool of key assets
     * @param actor     the actor on which the commands are executed
     * @param onIdle    the command to execute if the key pool is empty
     */
    public <K> void checkPool(final KeyAssetter<K> keyAssets, final T actor, final Command<T> onIdle) {
        if (!keyAssets.getUnmodifiablePool().isEmpty()) {
            keyAssets.getUnmodifiablePool().stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(inModel.getModel()::containsKey)
                    .map(inModel.getModel()::get)
                    .forEach(c -> c.execute(actor));
        } else {
            onIdle.execute(actor);
        }
    }
}
