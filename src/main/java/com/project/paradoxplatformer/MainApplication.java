package com.project.paradoxplatformer;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.ResourcesFinder;
import com.project.paradoxplatformer.view.Page;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainApplication extends Application implements ViewManager{

    private static Scene scene;
    private static Stage stage;
    private static List<Pair<Parent, Page<String>>> roots;
    private final Alert alert = new Alert(AlertType.NONE);
    
    @Override
    public void start(Stage primeStage) throws Exception {
        stage = primeStage;
        stage.setTitle("Paradox Platformer");
        stage.setOnCloseRequest(e -> System.exit(0));
        setScene("Paradox Platformer");
    }

    public MainApplication() {}

    public void setScene(String title) {
        roots = this.loadFXML();
        scene = new Scene(roots.get(Views.MAIN).getKey(), Color.rgb(237, 152, 112));
        stage.setScene(scene);      
        stage.show();
    
        // stage.minWidthProperty().set(MIN_DASHBOARD);
        // stage.minHeightProperty().set(MIN_WIDTH + MIN_GRID_HEGHT);
        // stage.maxHeightProperty().bind(stage.minHeightProperty().multiply(2));
        // stage.maxWidthProperty().bind(stage.minWidthProperty().multiply(2));

    }

    @Override
    public Page<String> switchView(int id){
        var entry = getParents(id);
        scene.setRoot(entry.getKey());
        stage.sizeToScene();
        return entry.getValue();
        
    }

    private Pair<Parent, Page<String>> getParents(int id) {
        return Optional.of(id).filter(i -> i < roots.size() && i >= 0 && !roots.isEmpty())
            .map(roots::get)
            .orElseThrow(IllegalArgumentException::new);
    }

    private List<Pair<Parent, Page<String>>> loadFXML() {
        return ResourcesFinder.FXMLfiles().stream()
            .map(FXMLLoader::new)
            .map(this::loadInput).toList();
    }

    private Pair<Parent, Page<String>> loadInput(FXMLLoader loader) {
        try  {
            Parent parent = loader.load();
            Page<String> controller = loader.getController();
            controller.create("level1.json");
            return Pair.of(parent, controller);
        } catch (IOException e) {
            this.displayError("IO exception error: " + e.getMessage());   
            System.exit(-1);
        } catch (Exception ex) {
            
            this.displayError(Optional.ofNullable(ex.getCause())
                .map(Throwable::getClass)
                .map(Class::getSimpleName)
                .orElse(ex.getClass().getSimpleName()) + " \nRaised → " +  
                Optional.ofNullable(ex.getCause())
                    .map(Throwable::getMessage)
                    .or(() -> Optional.ofNullable(ex.getCause())
                        .filter(RuntimeException.class::isInstance)
                        .map(RuntimeException.class::cast)
                        .map(RuntimeException::getMessage)
                    )
                .map(ex.getMessage()::concat)
                .orElse(ex.getMessage())
            ); 
            System.exit(-1);
        }
        return null;
    }

    @Override
    public void displayError(String content) {
        var al = new Alert(AlertType.ERROR);
        DialogPane p;
        try {
            p = new FXMLLoader(ResourcesFinder.getURL("diag-pane.fxml")).load();
            al.setDialogPane(p);
            this.setDialoContent(content, p);

        } catch (IOException | InvalidResourceException e) {
    
        }
        al.showAndWait();
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

    @Override
    public void displayMessage(String title, String header, String content) {
        this.setAndShowAlert(AlertType.INFORMATION, title, header, content);
        this.performReactiveAction(this.alert::showAndWait);
    }

    private void setAndShowAlert(AlertType alertType, String title, String header, String content) {
        this.alert.setAlertType(alertType);
        this.alert.setTitle(title);
        this.alert.setHeaderText(header);
        this.alert.setContentText(content);
    }

    @Override
    public void closeWithMessage(String header, String closingContent) {
        this.setAndShowAlert(AlertType.CONFIRMATION, "CLOSING", header, closingContent);
        this.performReactiveAction(() -> this.alert.showAndWait().ifPresent(b -> this.exit()));
    }

    @Override
    public void close() {
        this.exit();
    }

    @Override
    public void exit() {
        Platform.exit();
    }

    @Override
    public void performReactiveAction(Runnable runner) {
        Platform.runLater(runner);
    }
}
