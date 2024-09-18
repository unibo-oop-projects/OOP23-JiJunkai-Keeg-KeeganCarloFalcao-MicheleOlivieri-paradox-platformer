package com.project.paradoxplatformer.controller;

import java.util.concurrent.CountDownLatch;

import com.project.paradoxplatformer.controller.event.EventManager;
import com.project.paradoxplatformer.controller.event.GameEventType;
import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.utils.ExceptionUtils;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.view.legacy.ViewAdapterFactory;
import com.project.paradoxplatformer.view.manager.ViewManager;

/**
 * A simple implementation of the {@link Controller} interface that manages
 * the application's lifecycle, view switching, and initialization routines.
 * 
 * @param <N> the type of the main application view
 * @param <P> the type of the parameter passed to view creation
 * @param <K> the type of the view key
 */
public final class SimpleController<N, P, K> implements Controller {

    private final CountDownLatch latch = new CountDownLatch(1);
    private final ViewManager viewManager;
    private final String title;
    private final EventManager<GameEventType, PageIdentifier> eventManager;

    /**
     * Constructs a {@link SimpleController} with the specified view adapter and
     * title.
     * 
     * @param adapter the view adapter factory used to initialize the view manager
     * @param title   the title of the application window
     */
    public SimpleController(final ViewAdapterFactory<N, P, K> adapter, final String title) {
        this.viewManager = adapter.mainAppManager().get();
        this.title = title;
        this.eventManager = EventManager.getInstance();

        eventManager.subscribe(GameEventType.SWITCH_VIEW, this::handleViewSwitch);
        eventManager.subscribe(GameEventType.INITIALIZE, this::handleViewSwitch);
    }

    /**
     * Quits the application, closing the view manager with a confirmation message.
     */
    @Override
    public void quit() {
        this.viewManager.safeError(); // Force quit
    }

    /**
     * Starts the application, creating the main view and initializing routines.
     */
    @Override
    public void start() {
        try {
            new Thread(() -> viewManager.create(latch, title)).start();
            latch.await(); // waits on main thread that the application is started
            viewManager.runOnAppThread(this::initRoutine);
        } catch (InterruptedException | RuntimeException e) {
            // System.err.println("\nErrors encountered within view creation:\n → " +
            // ExceptionUtils.simpleDisplay(e));
            viewManager.safeError();
        }
    }

    /**
     * Handles view switch events by delegating to the
     * {@link #switchView(PageIdentifier, Level)} method.
     * 
     * @param id    the identifier of the page to switch to
     * @param param the level parameter associated with the view
     */
    private void handleViewSwitch(final PageIdentifier id, final Level param) {
        // System.out.println("NOW RECREATE THE VIEW.");
        this.switchView(id, param);
    }

    /**
     * Switches the view to the specified page and creates it with the provided
     * level parameter.
     * 
     * @param id    the identifier of the page to switch to
     * @param param the level parameter associated with the view
     */
    private void switchView(final PageIdentifier id, final Level param) {
        try {
            viewManager.switchPage(id).create(param);
        } catch (Exception ex) {
            viewManager.displayError(ExceptionUtils.advancedDisplay(ex));
            viewManager.safeError();
        }
    }

    /**
     * Initializes the routine by publishing an {@link GameEventType#INITIALIZE}
     * event.
     * This method is run on the application thread.
     */
    private void initRoutine() {
        this.eventManager.publish(GameEventType.INITIALIZE, PageIdentifier.MENU, Level.EMPTY_LEVEL);
        this.eventManager.unsubscribe(GameEventType.INITIALIZE);
    }
}
