package com.project.paradoxplatformer.controller;

import java.util.concurrent.CountDownLatch;

import com.project.paradoxplatformer.utils.EventManager;
import com.project.paradoxplatformer.utils.EventType;
import com.project.paradoxplatformer.utils.ExceptionUtils;
import com.project.paradoxplatformer.utils.Level;
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

        EventManager.getInstance().subscribe(EventType.SWITCH_VIEW, this::handleViewSwitch);
        EventManager.getInstance().subscribe(EventType.INITIALIZE, this::handleViewSwitch);

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

    private void handleViewSwitch(final PageIdentifier id, final Level level) {
        System.out.println("SIMPLE CONTROLLER FIRST!");
        this.switchView(id, level);
    }

    private void switchView(final PageIdentifier id, final Level level) {
        try {
            System.out.println("Switch to" + level);
            viewManager.switchPage(id).create(level.getResourceFile());
        } catch (Exception ex) {
            viewManager.displayError(ExceptionUtils.advacendDisplay(ex));
            viewManager.safeError();
        }
    }

    private void initRoutine() {
        EventManager.getInstance().publish(EventType.INITIALIZE, PageIdentifier.MENU, Level.EMPTY_LEVEL);
        // EventManager.getInstance().unsubscribe(EventType.INITIALIZE);
    }
}
