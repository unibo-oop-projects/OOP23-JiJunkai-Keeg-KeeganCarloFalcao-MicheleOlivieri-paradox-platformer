package com.project.paradoxplatformer.controller;

import java.util.concurrent.CountDownLatch;

import com.project.paradoxplatformer.model.effect.GameEventType;
import com.project.paradoxplatformer.model.effect.api.Level;
import com.project.paradoxplatformer.utils.EventManager;
import com.project.paradoxplatformer.utils.ExceptionUtils;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.view.legacy.ViewAdapterFactory;
import com.project.paradoxplatformer.view.manager.ViewManager;

/**
 * A simple controller to start and quit a view fx app. It basicaly holds the state of the view.
 * Generic class is implemented as for better awareness of what view such class is going to use. (it is not a necessity but rather a view indipendent interface)
 * <p> Such class handles thwo different threads (the app one and the main one). Note that thay are never mixed but perhaps sycronized.<p>
 * <p> While Controller interface does not depend upon view, this implementation does: it must know which view legacy must be used (swing, javafx, or console) <p>
 * @param <N> type for view node
 * @param <P> type for view pane
 * @param <K> type for view key
 */
public final class SimpleController<N, P, K> implements Controller {

    private final CountDownLatch latch = new CountDownLatch(1);
    private final ViewManager viewManager;
    private final String title;
    private final EventManager<GameEventType, PageIdentifier> eventManager;

    /**
     * Constructor where init routines events are subscribed
     * @param adapter to identify which 
     * @param title
     */
    public SimpleController(final ViewAdapterFactory<N, P, K> adapter, final String title) {
        this.viewManager = adapter.mainAppManager().get();
        this.title = title;
        this.eventManager = EventManager.getInstance();

        eventManager.subscribe(GameEventType.SWITCH_VIEW, this::handleViewSwitch);
        eventManager.subscribe(GameEventType.INITIALIZE, this::handleViewSwitch);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        this.viewManager.safeError(); //Force quit
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        try {
            new Thread(() -> viewManager.create(latch, title)).start();
            latch.await(); //waits on main thread that the application is started
            viewManager.runOnAppThread(this::initRoutine);
        } catch (InterruptedException | RuntimeException e) {
            System.err.println("\nErrors encounterd within view creation:\n → " + ExceptionUtils.simpleDisplay(e));
            this.quit();
        }
    }

    private void handleViewSwitch(final PageIdentifier id, final Level param) {
        System.out.println("NOW RECREATE THE VIEW.");
        this.switchView(id, param);
    }

    private void switchView(final PageIdentifier id, final Level param) {
        try {
            viewManager.switchPage(id).create(param.getResourceFile()); //creates the page (an important view update)
            this.eventManager.publish(GameEventType.UPDATE_HANDLER, id, param);
        } catch (Exception ex) {
            viewManager.displayError(ExceptionUtils.advacendDisplay(ex));
            this.quit();
        }
    }

    private void initRoutine() {
        this.eventManager.publish(GameEventType.INITIALIZE, PageIdentifier.MENU, Level.EMPTY_LEVEL);
        this.eventManager.unsubscribe(GameEventType.INITIALIZE);
    }
}
