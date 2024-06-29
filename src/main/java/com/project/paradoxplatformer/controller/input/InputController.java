package com.project.paradoxplatformer.controller.input;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.inputmodel.InputModel;
import com.project.paradoxplatformer.model.world.ModelData;
import com.project.paradoxplatformer.view.fxcomponents.keyinputs.api.FXKeyAssetter;

public class InputController {
    
    private final FXKeyAssetter keyAssets;
    private final InputModel inModel;

    public InputController(final InputModel inModel, final FXKeyAssetter assets) {
        this.inModel = inModel;
        this.keyAssets = assets;
    }

    public void inject(final ModelData modelData) {
        keyAssets.cyclePool(inModel, modelData.getWorld().player());
    }
}
