package com.project.paradoxplatformer.model.inputmodel;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;

public interface InputMovesFactory {
    
    InputModel<ControllableObject> standardModel();

    InputModel<ControllableObject> wasdModel();

    InputModel<ControllableObject> advancedModel();

    InputModel<ControllableObject> invertedModel(InputModel<ControllableObject> model);

}
