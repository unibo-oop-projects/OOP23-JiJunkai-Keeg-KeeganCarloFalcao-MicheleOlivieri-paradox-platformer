package com.project.paradoxplatformer.controller.input;

import com.project.paradoxplatformer.controller.input.api.KeyAssetter;
import com.project.paradoxplatformer.model.inputmodel.InputModel;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;
import com.project.paradoxplatformer.utils.SecureWrapper;

public class InputController<T> {
    
    //NEED TO MAKE FXKEY ASSETTER A FX INDEPENDENT CONCEPT ::DONE::
    private final SecureWrapper<KeyAssetter> keyAssets;
    private final InputModel<T> inModel;

    public InputController(final InputModel<T> inModel, final KeyAssetter assets) {
        this.inModel = inModel;
        this.keyAssets = SecureWrapper.of(assets);
    }

    public void inject(T actor, Command<T> onIdle) {
        //MAKE IMMUTABLE KEYASSETS LIST SO I CAN USE IT HERE AND NOT IN VIEW
        //CHECK DONE
        this.keyAssets.get().cyclePool(inModel, actor, onIdle);
    }
}
