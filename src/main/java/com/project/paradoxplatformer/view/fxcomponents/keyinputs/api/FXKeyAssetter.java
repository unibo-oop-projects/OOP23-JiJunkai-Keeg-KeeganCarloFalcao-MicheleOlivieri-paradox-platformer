package com.project.paradoxplatformer.view.fxcomponents.keyinputs.api;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.inputmodel.InputModel;

import javafx.scene.input.KeyEvent;

public interface FXKeyAssetter {

    public boolean remove(KeyEvent e);

    public boolean add(KeyEvent e);

    void cyclePool(InputModel modelInput, ControllableObject executor);

}
