package com.project.paradoxplatformer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

public class MainApplication extends Application implements ViewManager{

    private static final double MIN_DASHBOARD = 330;
    private static final int MIN_WIDTH = 330;
    private static final int MIN_GRID_HEGHT = 70;
    private static Scene scene;
    private static Stage stage;
    private static List<Pair<Parent, Object>> roots;
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
        roots = loadFXML();
        scene = new Scene(roots.get(Views.MAIN).getKey(), MIN_DASHBOARD, MIN_WIDTH + MIN_GRID_HEGHT);
        //COULD BE DONE ON FXML [SEPARATE UI WITH CODE]
        scene.getStylesheets().add(MainApplication.class.getResource("style.css").toExternalForm());
        stage.setScene(scene);      
        stage.show();
    
        
        stage.minWidthProperty().set(MIN_DASHBOARD);
        stage.minHeightProperty().set(MIN_WIDTH + MIN_GRID_HEGHT);
        stage.maxHeightProperty().bind(stage.minHeightProperty().multiply(2));
        stage.maxWidthProperty().bind(stage.minWidthProperty().multiply(2));

    }

    @Override
    public Object switchView(int id){
        var entry = getParents(id);
        scene.setRoot(entry.getKey());
        stage.sizeToScene();
        return entry.getValue();
    }

    private Pair<Parent, Object> getParents(int id) {
        return Optional.of(id).filter(i -> i < roots.size() && i >= 0 && !roots.isEmpty())
            .map(roots::get)
            .orElseThrow(IllegalArgumentException::new);
    }

    private List<Pair<Parent, Object>> loadFXML() {
        return ResourcesFinder.FXMLfiles().stream()
            .map(FXMLLoader::new)
            .map(this::loadInput).toList();
    }

    private <T> Pair<Parent, T> loadInput(FXMLLoader loader) {
        try  {
            Parent parent = loader.load();
            T controller = loader.getController();
            return Pair.of(parent, controller);
        } catch (IOException e) {
            Alert al = new Alert(AlertType.ERROR, e.getMessage());
            al.showAndWait();
            e.printStackTrace();
        }
        return null;
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
        System.exit(0);
    }

    @Override
    public void performReactiveAction(Runnable runner) {
        Platform.runLater(runner);
    }
}
