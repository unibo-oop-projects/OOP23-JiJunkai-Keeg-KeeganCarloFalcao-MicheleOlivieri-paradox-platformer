package com.project.paradoxplatformer.view;

import java.util.concurrent.CountDownLatch;

import com.project.paradoxplatformer.view.javafx.PageIdentifier;

public interface ViewManager {

    Object switchPage(final PageIdentifier pageID);

    void create(final String title);

    void create(final CountDownLatch latch, final String title);

    void displayMessage(String title, String header, String content);

    void displayError(String content);

    void closeWithMessage(String header, String closingContent);

    void exit();

    void safeError();

    void runOnAppThread(Runnable runner);

}
