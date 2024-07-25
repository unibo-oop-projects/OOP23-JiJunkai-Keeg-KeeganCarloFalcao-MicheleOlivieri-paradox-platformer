package com.project.paradoxplatformer.controller.games;

import com.project.paradoxplatformer.controller.input.InputController;
import com.project.paradoxplatformer.controller.input.api.KeyInputer;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.InvalidResourceException;

public interface GameController<C> {

    void loadModel();

    void syncView() throws InvalidResourceException;

    void startGame(InputController<ControllableObject> ic, KeyInputer inputer);

}
