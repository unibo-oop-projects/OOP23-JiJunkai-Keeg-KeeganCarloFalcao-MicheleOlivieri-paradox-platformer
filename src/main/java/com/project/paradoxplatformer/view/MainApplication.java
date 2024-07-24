package com.project.paradoxplatformer.view;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.ResourcesFinder;
import com.project.paradoxplatformer.utils.geometries.Dimension;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class MainApplication extends Application implements ViewManager{

    private static Scene scene;
    private static Stage stage;
    private static List<Pair<Parent, Page<String>>> roots;
    private boolean created;
    private static CountDownLatch latch;
    private static final double ASPECT_RATIO = 16 / 9.d;

    public MainApplication() {
        this.created = true;
    }
    
    @Override
    public synchronized void start(Stage primeStage) throws Exception {
        if(!created) {
            throw new IllegalStateException("Cannot create application, Security reasons");
        }
        stage = primeStage;
        stage.setOnCloseRequest(e -> this.close());
        //COuld be done dynamically hwen pages are called, loads slower
        try {
            roots = this.loadFXML();
        } catch(RuntimeException ex) {
            this.displayError(ex.getMessage());
            latch.countDown();
            this.safeError();
        }
        setScene("Paradox Platformer");
        stage.show();
        latch.countDown();
        
    }

    @Override
    public void create() {
        MainApplication.launch();
    }

    //CAN PASS ONLY REF SO FB SHUT
    @Override
    public void create(final CountDownLatch referedLatch) {
        latch = referedLatch;
        this.create();
    }

    @Override
    public Page<String> switchPage(PageIdentifier id) {
        if(Platform.isFxApplicationThread()) {
            var entry = this.getParents(id.ordinal());
            scene.setRoot(entry.getKey());
            // stage.setScene(new Scene(entry.getKey()));
            stage.sizeToScene();
            return entry.getValue();
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
        this.runOnAppThread(() -> alert.showAndWait().ifPresent(b -> this.exit()));
    }

    @Override
    public void close() {
        stage.close();
    }

    @Override
    public void safeError() {
        this.exit();
    }

    @Override
    public void exit() {
        Platform.exit();
    }

    @Override
    public void runOnAppThread(Runnable runner)  {
        Platform.runLater(runner);
    }

    private void setScene(String title) {
        var primaryScreenBounds = Screen.getPrimary().getBounds();
        var dim = new Dimension(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        System.out.println(dim);
        final double resoultion = 360;
        scene = new Scene(new Pane(), resoultion*ASPECT_RATIO, resoultion, Color.rgb(237, 152, 112));
        stage.sizeToScene();
        stage.setScene(scene); 
        
    }

    //TODO REIMPLEMENT
    private Pair<Parent, Page<String>> getParents(int id) {
        return Optional.of(id).filter(i -> i < roots.size() && i >= 0 && !roots.isEmpty())
            .map(roots::get)
            .orElseThrow(IllegalArgumentException::new);
    }

    private List<Pair<Parent, Page<String>>> loadFXML() throws InvalidResourceException{
        return ResourcesFinder.FXMLfiles().stream()
            .map(FXMLLoader::new)
            .map(this::loadInput).toList();
    }

    private Pair<Parent, Page<String>> loadInput(FXMLLoader loader) {
        try  {
            Parent parent = loader.load();
            Page<String> controller = loader.getController();
            
            return Pair.of(parent, controller);
        } catch (IOException e) {
            this.displayError("IO exception error: " + e.getMessage());   
            System.exit(-1);
        } 
        return null;
    }

    private void setDialoContent(String content, DialogPane p) {
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

    private void setAndShowAlert(Alert alert, AlertType alertType, String title, String header, String content) {
        alert.setAlertType(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
    }
}
