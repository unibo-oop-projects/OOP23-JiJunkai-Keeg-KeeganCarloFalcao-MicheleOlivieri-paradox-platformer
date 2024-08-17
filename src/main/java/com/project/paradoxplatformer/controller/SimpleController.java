package com.project.paradoxplatformer.controller;

import java.util.concurrent.CountDownLatch;

import com.project.paradoxplatformer.view.ViewManager;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.view.legacy.ViewAdapterFactory;

public final class SimpleController<N, P, K> implements Controller {

    private final CountDownLatch latch = new CountDownLatch(1);
    private final ViewManager viewManager;
    private final String title;

    public SimpleController(final ViewAdapterFactory<N, P, K> adapter, final String title) {
        viewManager = adapter.mainAppManager().get();
        this.title = title;
    }

    @Override
    public void quit() {
        this.viewManager.closeWithMessage("Closing...", "Are you sure you want to exit?");
    }

    @Override
    public void start() {
        try {
            new Thread(() -> viewManager.create(latch, title)).start();
            latch.await();
            System.out.println("Application Started");
            viewManager.runOnAppThread(() -> this.switchView(PageIdentifier.GAME, "level1.json"));// IT MUST BE MENU
        } catch (InterruptedException | RuntimeException e) {
            System.err.println("\nErrors encounterd within view creation:\n → " + ExceptionUtils.simpleDisplay(e));
            viewManager.safeError();
        }
    }

    private void switchView(final PageIdentifier id, final String param) {
        try {
            viewManager.switchPage(id).create(param);
        } catch (Exception ex) {
            viewManager.displayError(ExceptionUtils.advacendDisplay(ex));
            viewManager.safeError();
        }
    }
}
