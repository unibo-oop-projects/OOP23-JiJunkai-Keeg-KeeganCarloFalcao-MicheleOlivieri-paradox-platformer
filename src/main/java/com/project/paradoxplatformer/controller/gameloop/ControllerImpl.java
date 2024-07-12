package com.project.paradoxplatformer.controller.gameloop;

import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.controller.input.InputController;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.world.ModelData;

public class ControllerImpl implements Controller {

    private final GameController gController;
    private final InputController<ControllableObject> inputController;
    private final ModelData model;

    public ControllerImpl(final GameController gController, final InputController<ControllableObject> iC, final ModelData m) {
        this.gController = gController;
        this.inputController = iC;
        this.model = m;
    }

    @Override
    public void updateTimer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTimer'");
    }

    @Override
    public void updateGame(final long dt) {
        this.inputController.inject(model.getWorld().player(), ControllableObject::stop);
        this.gController.update(dt);
    }

    @Override
    public void quit() {
        //Proply use view to close
        System.exit(0);
    }
    
}
