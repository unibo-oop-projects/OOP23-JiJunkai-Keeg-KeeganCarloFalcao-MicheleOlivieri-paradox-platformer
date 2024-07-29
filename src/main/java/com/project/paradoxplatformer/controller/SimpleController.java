package com.project.paradoxplatformer.controller;

import java.util.concurrent.CountDownLatch;


import com.project.paradoxplatformer.view.ViewManager;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.view.legacy.ViewAdapterFactory;

public final class SimpleController<N, P, K> implements Controller {

    private final CountDownLatch latch = new CountDownLatch(1);
    private final ViewManager view;
    private final String title;

    public SimpleController(final ViewAdapterFactory<N, P, K> adapter, final String title) {
        view = adapter.mainAppManager().get();
        this.title = title;
    }

    @Override
    public void quit() {
        this.view.closeWithMessage("Closing...", "Are you sure you want to exit?");
    }

    @Override
    public void start() {
        try {
            new Thread(() -> view.create(latch, title)).start();
            latch.await();
            System.out.println("Application Started");
            view.runOnAppThread(() -> this.switchView(PageIdentifier.GAME, "level1g.json"));// IT MUST BE MENU
        } catch (InterruptedException | IllegalStateException e) {
            System.err.println("\nErrors encounterd within view creation:\n → " + ExceptionUtils.simpleDisplay(e));
            view.safeError();
        }
        
    }

    private void switchView(final PageIdentifier id, final String param) {
        try {
            view.switchPage(id).create(param);
        } catch (Exception ex){
            view.displayError(ExceptionUtils.advacendDisplay(ex));
            view.safeError();
        }
    }
}
