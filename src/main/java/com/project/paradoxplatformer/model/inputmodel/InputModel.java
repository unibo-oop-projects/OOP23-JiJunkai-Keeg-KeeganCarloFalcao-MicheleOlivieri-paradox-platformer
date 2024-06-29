package com.project.paradoxplatformer.model.inputmodel;


import com.project.paradoxplatformer.utils.commands.Command;
import com.project.paradoxplatformer.view.fxcomponents.keyinputs.api.InputType;

import java.util.EnumMap;

public interface InputModel {
    EnumMap<InputType, Command> getModel();
}
