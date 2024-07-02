package com.project.paradoxplatformer.model.inputmodel;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;

public interface InputFactory {
    
    InputModel<ControllableObject> standardModel();

    InputModel<ControllableObject> wasdModel();

    InputModel<ControllableObject> advancedModel();

    InputModel<ControllableObject> invertedModel(InputModel<ControllableObject> model);

}
