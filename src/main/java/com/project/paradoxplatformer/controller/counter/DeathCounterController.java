package com.project.paradoxplatformer.controller.counter;

import com.project.paradoxplatformer.model.counter.DeathCounterModel;
import com.project.paradoxplatformer.view.counter.DeathCounterView;

public class DeathCounterController {
    private DeathCounterModel model;
    private DeathCounterView view;

    public DeathCounterController(DeathCounterModel model, DeathCounterView view) {
        this.model = model;
        this.view = view;
        this.model.addObserver(view);
    }

    public void incrementDeathCount() {
        model.incrementCount();
    }

    public void resetDeathCount() {
        model.resetCount();
    }

    public void updateView() {
        view.displayCount();
    }
}
