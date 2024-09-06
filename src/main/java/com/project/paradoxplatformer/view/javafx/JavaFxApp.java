package com.project.paradoxplatformer.view.javafx;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.controller.ExceptionUtils;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.ResourcesFinder;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.Page;
import com.project.paradoxplatformer.view.ViewManager;
import com.project.paradoxplatformer.view.legacy.ViewLegacy;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class JavaFxApp extends Application implements ViewManager {

    private static Scene scene;
    private static Stage stage;
    private static FXMLPageHelper helper;
    private boolean created;
    private static String staticTitle = "";
    private static CountDownLatch latch;

    private static final class LazyHolder {
        private static final ViewManager SINGLETON = new JavaFxApp();
    }

    public static ViewManager getInstance() {
        return LazyHolder.SINGLETON;
    }

    public static Scene createScene(Parent root) {
        return new Scene(root);
    }

    public JavaFxApp() {
    }

    @Override
    public void init() {
        this.created = true;
    }

    @Override
    public void start(Stage primeStage) throws IOException {
        if (!created) {
            throw new IllegalStateException("Cannot create application, Security reasons");
        }
        stage = primeStage;
        stage.setOnCloseRequest(e -> this.exit());
        // COuld be done dynamically hwen pages are called, loads slower
        try {
            helper = new FXMLPageHelper();
        } catch (InvalidResourceException | RuntimeException ex) {
            this.displayError(ExceptionUtils.advacendDisplay(ex));
            this.safeError();
        }
        stage.setTitle(staticTitle);
        this.setInitialScene();
        stage.show();
        Optional.ofNullable(latch).ifPresent(CountDownLatch::countDown);

    }

    @Override
    public void create(final String appTitle) {
        staticTitle = appTitle;
        JavaFxApp.launch();
    }

    // CAN PASS ONLY REF SO FB SHUT
    @Override
    public void create(final CountDownLatch referedLatch, final String appTitle) {
        latch = referedLatch;
        this.create(appTitle);
    }

    @Override
    public Page<String> switchPage(PageIdentifier id) {
        if (Platform.isFxApplicationThread()) {

            System.out.println("In SWITCH PANE FUNCTION");

            System.out.println("[CURRENT ID]: " + id);

            var entry = helper.mapper().apply(id);
            scene.setRoot(
                entry.map(Pair::getKey)
                    .orElse(ViewLegacy.javaFxFactory().blankPage())
                );
            stage.sizeToScene();

            System.out.println("[PANE]: " + entry.map(Pair::getValue).orElse(Page.defaultPage()));

            return entry.map(Pair::getValue).orElse(Page.defaultPage());
        }
        throw new IllegalStateException("Not in FX Thread");

    }

    @Override
    public void displayMessage(String title, String header, String content) {
        final Alert alert = new Alert(AlertType.NONE);
        this.setAndShowAlert(alert, AlertType.INFORMATION, title, header, content);
        this.runOnAppThread(alert::showAndWait);
    }

    @Override
    public void displayError(String content) {
        var al = new Alert(AlertType.ERROR, content);
        DialogPane errorPane;
        try {
            errorPane = new FXMLLoader(ResourcesFinder.getURL("diag-pane.fxml")).load();
            al.setDialogPane(errorPane);
            this.setDialoContent(content, errorPane);

        } catch (IOException | InvalidResourceException | ClassCastException e) {
            al.setHeaderText("Custom Alert failed, showing Default Alert");
            al.setContentText(content + "\n\nWhy custom alert failed to load? ¬"
                    + "\n" + ExceptionUtils.advacendDisplay(e));
        } finally {
            al.showAndWait();
        }

    }

    @Override
    public void closeWithMessage(String header, String closingContent) {
        final Alert alert = new Alert(AlertType.NONE);
        this.setAndShowAlert(alert, AlertType.CONFIRMATION, "CLOSING", header, closingContent);
        alert.showAndWait().ifPresent(b -> this.exit());
    }

    @Override
    public void safeError() {
        this.exit();
        System.exit(-1);
    }

    @Override
    public void exit() {
        Platform.exit();
    }

    @Override
    public void runOnAppThread(Runnable runner) {
        Platform.runLater(runner);
    }

    private void setInitialScene() {
        var primaryScreenBounds = Screen.getPrimary().getBounds();
        var dim = new Dimension(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        System.out.println(dim);
        final double resoultion = 360;
        LinearGradient paint = new LinearGradient(
                0.9762, 0.0, 1.0, 1.0, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, new Color(1.0, 0.3924, 0.02, 1.0)),
                new Stop(1.0, new Color(0.6842, 0.4257, 0.038, 1.0)));
        scene = new Scene(ViewLegacy.javaFxFactory().loadingPage(), resoultion * ASPECT_RATIO, resoultion, paint);
        stage.sizeToScene();
        stage.setScene(scene);

    }

    private void setDialoContent(final String content, final DialogPane p) throws ClassCastException {
        p.getChildren().stream()
                .filter(VBox.class::isInstance)
                .map(VBox.class::cast)
                .map(VBox::getChildren)
                .flatMap(ObservableList::stream)
                .filter(Label.class::isInstance)
                .map(Label.class::cast)
                .findFirst()
                .ifPresent(l -> l.setText(content));
    }

    private void setAndShowAlert(
            final Alert alert,
            final AlertType alertType,
            final String title,
            final String header,
            final String content) {
        alert.setAlertType(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
    }
}
