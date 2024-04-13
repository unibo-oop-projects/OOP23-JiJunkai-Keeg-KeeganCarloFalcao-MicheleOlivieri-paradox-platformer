package com.project.paradoxplatformer.view.counter;

import com.project.paradoxplatformer.model.counter.DeathCounterModel;
import com.project.paradoxplatformer.utils.world.observer.Observer;

public class DeathCounterView implements Observer {

    private DeathCounterModel model;

    public DeathCounterView(DeathCounterModel model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update() {
        displayCount();
    }

    private void displayCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayCount'");
    }

}
