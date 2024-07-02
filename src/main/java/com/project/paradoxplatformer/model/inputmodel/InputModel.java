package com.project.paradoxplatformer.model.inputmodel;


import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;

import java.util.EnumMap;

public interface InputModel<T> {
    EnumMap<InputType, Command<T>> getModel();
}
