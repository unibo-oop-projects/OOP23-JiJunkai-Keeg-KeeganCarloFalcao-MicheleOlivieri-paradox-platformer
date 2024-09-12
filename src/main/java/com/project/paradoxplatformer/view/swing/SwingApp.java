package com.project.paradoxplatformer.view.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.concurrent.CountDownLatch;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.view.Page;
import com.project.paradoxplatformer.view.ViewManager;
import com.project.paradoxplatformer.view.javafx.FXMLPageHelper;
import com.project.paradoxplatformer.view.javafx.JavaFxApp;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.view.legacy.ViewLegacy;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
public final class SwingApp extends JFrame implements ViewManager{

    private final JPanel jpane;
    private CountDownLatch latch;
    private final FXMLPageHelper<Page<String>> helper;

    public SwingApp() {
        this.jpane = new JPanel(new BorderLayout());
        this.setContentPane(this.jpane);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        var resoultion = 360;
        this.setSize((int) (resoultion * ASPECT_RATIO), resoultion);
        this.getContentPane().setBackground(Color.WHITE);
        try {
            helper = new FXMLPageHelper<>();
        } catch(InvalidResourceException ex) {
            throw new IllegalStateException(ex.getMessage(), ex);
        }
    }

    @Override
    public Page<String> switchPage(final PageIdentifier pageID) {
        final JFXPanel fJfxPanel = new JFXPanel();
        this.setContentPane(fJfxPanel);
        fJfxPanel.requestFocus();
        return emebedFXPage(fJfxPanel, pageID);
    }

    @Override
    public void create(String title) {
        this.setTitle(title);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.latch.countDown();
    }

    @Override
    public void create(CountDownLatch latch, String title) {
        this.latch = latch;
        this.create(title);
    }

    @Override
    public void displayMessage(String title, String header, String content) {
        JOptionPane.showMessageDialog(this, content, title, JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void displayError(String content) {
        JOptionPane.showMessageDialog(this, content, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void closeWithMessage(String header, String closingContent) {
        JOptionPane.showConfirmDialog(this, closingContent, "CLOSING", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void exit() {
        this.dispose();
    }

    @Override
    public void safeError() {
        this.dispose();
        System.exit(-1);
    }

    @Override
    public void runOnAppThread(Runnable runner) {
        SwingUtilities.invokeLater(runner);
    }

    private Page<String> emebedFXPage(final JFXPanel fxPanel, final PageIdentifier pageID) {
        final var entry = helper.mapper().apply(pageID);
        final Scene fxScene = entry
            .map(Pair::getKey)
            .map(JavaFxApp::createScene)
            .orElse(
                JavaFxApp.createScene(
                        ViewLegacy.javaFxFactory().blankPage()
                    )
            );
        entry.map(Pair::getValue).orElse(Page.defaultPage());
        fxPanel.setScene(fxScene);
        return entry.map(Pair::getValue).orElse(Page.defaultPage());
    }
    
}
