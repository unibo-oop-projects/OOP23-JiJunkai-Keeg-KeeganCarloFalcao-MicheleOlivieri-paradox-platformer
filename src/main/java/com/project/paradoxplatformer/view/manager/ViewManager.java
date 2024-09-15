package com.project.paradoxplatformer.view.manager;

import java.util.concurrent.CountDownLatch;

import com.project.paradoxplatformer.view.Page;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

/**
 * Interface for managing views in the application.
 * Provides methods for switching pages, creating views, displaying messages,
 * handling errors, and managing the application lifecycle.
 */
public interface ViewManager {

    /**
     * Aspect ratio of the application views (16:9).
     */
    double ASPECT_RATIO = 16 / 9.d;

    /**
     * Switches to a different page identified by the given PageIdentifier.
     *
     * @param pageID the identifier of the page to switch to
     * @return the Page instance associated with the given identifier
     */
    Page<String> switchPage(PageIdentifier pageID);

    /**
     * Creates and initializes a view with the given title.
     *
     * @param title the title of the view
     */
    void create(String title);

    /**
     * Creates and initializes a view with the given title and a CountDownLatch
     * for synchronization.
     *
     * @param latch the CountDownLatch for synchronization
     * @param title the title of the view
     */
    void create(CountDownLatch latch, String title);

    /**
     * Displays a message to the user with the given title, header, and content.
     *
     * @param title   the title of the message
     * @param header  the header of the message
     * @param content the content of the message
     */
    void displayMessage(String title, String header, String content);

    /**
     * Displays an error message to the user with the given content.
     *
     * @param content the content of the error message
     */
    void displayError(String content);

    /**
     * Closes the application with a message containing the given header and closing
     * content.
     *
     * @param header         the header of the closing message
     * @param closingContent the content of the closing message
     */
    void closeWithMessage(String header, String closingContent);

    /**
     * Exits the application.
     */
    void exit();

    /**
     * Safely handles errors, potentially logging them or displaying them to the
     * user.
     */
    void safeError();

    /**
     * Executes a Runnable on the application's main thread.
     *
     * @param runner the Runnable to be executed on the main thread
     */
    void runOnAppThread(Runnable runner);
}
