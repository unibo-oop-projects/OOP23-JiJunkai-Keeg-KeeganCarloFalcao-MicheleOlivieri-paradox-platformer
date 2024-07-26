package com.project.paradoxplatformer.controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import com.project.paradoxplatformer.view.ViewManager;
import com.project.paradoxplatformer.view.javafx.JavaFxApp;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

public final class SimpleController implements Controller {

    private final CountDownLatch latch = new CountDownLatch(1);
    private final ViewManager view;
    private final String title;

    public SimpleController(final ViewManager vm, final String title) {
        view = vm;
        this.title = title;
    }

    @Override
    public void quit() {
        this.view.closeWithMessage("Closing...", "Are you sure you want to exit?");
    }

    @Override
    public void start() {
        new Thread(() -> view.create(latch, title)).start();
        System.out.println(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        try {
            latch.await();
            System.out.println("Application Started");
            view.runOnAppThread(() -> this.switchView(PageIdentifier.GAME, "level1.json"));// IT MUST BE MENU
        } catch (InterruptedException | IllegalStateException e) {
            System.err.println("\nErrors encounterd within view creation:\n → " + ExceptionUtils.simpleDisplay(e));
            view.safeError();
        }
        
    }

    private void switchView(final PageIdentifier id, final String param) {
        if (view instanceof JavaFxApp ma) {
            try {
                ma.switchPage(id).create(param);
            } catch (Exception ex){
                view.displayError(ExceptionUtils.advacendDisplay(ex));
                view.safeError();
            }
        }
    }
}
