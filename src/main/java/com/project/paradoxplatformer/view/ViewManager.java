package com.project.paradoxplatformer.view;

import java.util.concurrent.CountDownLatch;

public interface ViewManager {

    Object switchPage(PageIdentifier pageID);

    void create();

    void create(final CountDownLatch latch);

    void displayMessage(String title, String header, String content);

    void displayError(String content);

    void closeWithMessage(String header, String closingContent);

    void close();

    void exit();

    void safeError();

    void runOnAppThread(Runnable runner);

}
