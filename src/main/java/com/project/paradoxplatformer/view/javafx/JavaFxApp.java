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
import com.project.paradoxplatformer.view.PageIdentifier;
import com.project.paradoxplatformer.view.ViewManager;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class JavaFxApp extends Application implements ViewManager{

    private static Scene scene;
    private static Stage stage;
    private static FXMLHelper helper;
    private boolean created;
    private String title;
    private static CountDownLatch latch;
    private static final double ASPECT_RATIO = 16 / 9.d;

    public JavaFxApp() {
        this.created = true;
        this.title = "";
    }
    
    @Override
    public void start(Stage primeStage) throws IOException {
        if(!created) {
            throw new IllegalStateException("Cannot create application, Security reasons");
        }
        stage = primeStage;
        stage.setOnCloseRequest(e -> this.exit());
        //COuld be done dynamically hwen pages are called, loads slower
        try {
            helper = new FXMLHelper();
        } catch(InvalidResourceException | RuntimeException ex) {
            this.displayError(ExceptionUtils.advacendDisplay(ex));
            this.safeError();
        }
        stage.setTitle(this.title);
        this.setInitialScene();
        stage.show();
        Optional.ofNullable(latch).ifPresent(CountDownLatch::countDown);
        
    }

    @Override
    public void create(final String title) {
        JavaFxApp.launch();
    }

    //CAN PASS ONLY REF SO FB SHUT
    @Override
    public void create(final CountDownLatch referedLatch, final String title) {
        latch = referedLatch;
        this.create(title);
    }

    @Override
    public Page<String> switchPage(PageIdentifier id) {
        if(Platform.isFxApplicationThread()) {
            var entry = helper.mapper().apply(id);
            entry.map(Pair::getKey).ifPresentOrElse(scene::setRoot, () -> scene.setRoot(new StackPane(new Label("BLANK PAGE"))));
            stage.sizeToScene();
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
        DialogPane p;
        try {
            p = new FXMLLoader(ResourcesFinder.getURL("diag-pane.fxml")).load();
            al.setDialogPane(p);
            this.setDialoContent(content, p);

        } catch (IOException | InvalidResourceException e) {
            //SISYEM ERROR HANDLER
        }
        al.showAndWait();
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
    public void runOnAppThread(Runnable runner)  {
        Platform.runLater(runner);
    }

    private void setInitialScene() {
        var primaryScreenBounds = Screen.getPrimary().getBounds();
        var dim = new Dimension(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        System.out.println(dim);
        final double resoultion = 360;
        scene = new Scene(new Pane(new Label("LOADING...")), resoultion*ASPECT_RATIO, resoultion, Color.TURQUOISE);
        stage.sizeToScene();
        stage.setScene(scene); 
        
    }

    private void setDialoContent(final String content, final DialogPane p) {
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
        final String content
    ) {
        alert.setAlertType(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
    }
}
