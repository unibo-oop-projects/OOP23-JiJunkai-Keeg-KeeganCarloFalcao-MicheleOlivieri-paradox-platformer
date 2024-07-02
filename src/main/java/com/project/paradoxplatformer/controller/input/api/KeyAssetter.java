package com.project.paradoxplatformer.controller.input.api;

import com.project.paradoxplatformer.model.inputmodel.InputModel;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;


public interface KeyAssetter {

    public boolean remove(final InputType e);

    public boolean add(final InputType e);

    <T> void cyclePool(final InputModel<T> modelInput, T executor, Command<T> onIdle);

}
