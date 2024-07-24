package com.project.paradoxplatformer.controller.input;

import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.controller.input.api.KeyAssetter;
import com.project.paradoxplatformer.model.inputmodel.InputModel;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;

public class InputController<T> {
    
    //NEED TO MAKE FXKEY ASSETTER A FX INDEPENDENT CONCEPT ::DONE::
    private final InputModel<T> inModel;

    public InputController(final InputModel<T> inModel) {
        this.inModel = inModel;
    }

    public <K> void cyclePool(final KeyAssetter<K> keyAssets, final T actor, final Command<T> onIdle) {
        if(!keyAssets.getUnmodifiablePool().isEmpty()) {
            keyAssets.getUnmodifiablePool().stream()
                .filter(in -> !in.equals(InputType.UNDEFINED))
                .filter(inModel.getModel()::containsKey)
                .map(inModel.getModel()::get)
                .forEach(c -> c.execute(actor));
        }
        else {
            onIdle.execute(actor);
        }
    }
}
