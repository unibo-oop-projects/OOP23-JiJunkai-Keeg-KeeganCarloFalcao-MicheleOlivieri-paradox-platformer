package com.project.paradoxplatformer.view.timer;

import com.project.paradoxplatformer.model.timer.TimerModel;
import com.project.paradoxplatformer.utils.world.observer.Observer;

public class TimerView implements Observer {

    private TimerModel model;

    public TimerView(TimerModel model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update() {
        displayTimeElapsed();
    }

    private void displayTimeElapsed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayTimeElapsed'");
    }

}
