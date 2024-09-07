package com.project.paradoxplatformer.model.inputmodel;


import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;

import java.util.Map;

public interface InputModel<T> {
    Map<InputType, Command<T>> getModel();
}
