package com.project.paradoxplatformer.controller;

import java.util.concurrent.CountDownLatch;

import com.project.paradoxplatformer.utils.EventManager;
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

        EventManager.getInstance().subscribe("SWITCH_VIEW", this::handleViewSwitch);
        EventManager.getInstance().subscribe("INITIALIZE", this::handleViewSwitch);

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
            System.out.println("Application Thread Started");
            viewManager.runOnAppThread(this::initRoutine);
        } catch (InterruptedException | RuntimeException e) {
            System.err.println("\nErrors encounterd within view creation:\n → " + ExceptionUtils.simpleDisplay(e));
            viewManager.safeError();
        }
    }

    private void handleViewSwitch(final PageIdentifier id, final String param) {
        this.switchView(id, param);
    }

    private void switchView(final PageIdentifier id, final String param) {
        try {
            viewManager.switchPage(id).create(param);
        } catch (Exception ex) {
            viewManager.displayError(ExceptionUtils.advacendDisplay(ex));
            viewManager.safeError();
        }
    }

    private void initRoutine() {
        EventManager.getInstance().publish("INITIALIZE", PageIdentifier.MENU, "");
    }
}
