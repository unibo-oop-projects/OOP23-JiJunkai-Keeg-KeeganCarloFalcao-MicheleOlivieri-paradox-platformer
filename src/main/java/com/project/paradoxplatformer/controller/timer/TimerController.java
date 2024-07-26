package com.project.paradoxplatformer.controller.timer;

import com.project.paradoxplatformer.model.timer.TimeProviderModel;
import com.project.paradoxplatformer.model.timer.TimerModel;
import com.project.paradoxplatformer.model.timer.api.TimeProvider;
import com.project.paradoxplatformer.view.timer.TimerView;

public  final class TimerController {
    private static TimerController instance;
    private TimerModel model;
    private TimerView view;
    private TimeProvider timeProvider;

    private TimerController() {
        timeProvider = new TimeProviderModel();
        model = new TimerModel(timeProvider);
        view = new TimerView(model);
    }

    public static TimerController getInstance() {
        if (instance == null) {
            instance = new TimerController();
        }
        return instance;
    }

    public TimerModel getModel() {
        return model;
    }

    public void startTimer() {
        model.startTimer();
    }

    public void stopTimer() {
        model.stopTimer();
    }

    public long getTimeElapsed() {
        return model.getTimeElapsed();
    }

    public void adjustSpeed(double speedMultiplier) {
        timeProvider.adjustSpeed(speedMultiplier);
        model.notifyObservers();
    }

    public void updateView() {
        view.displayTimeElapsed();
    }
}
