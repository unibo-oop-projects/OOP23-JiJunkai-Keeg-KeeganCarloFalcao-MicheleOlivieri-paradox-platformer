package com.project.paradoxplatformer.model.inputmodel;

import com.project.paradoxplatformer.model.menu.api.Command;
import com.project.paradoxplatformer.view.fxcomponents.keyinputs.InputType;
import java.util.EnumMap;

public interface InputModel {
    EnumMap<InputType, Command> getModel();
}
