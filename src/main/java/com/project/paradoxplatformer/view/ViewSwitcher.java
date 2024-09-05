package com.project.paradoxplatformer.view;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.ResourcesFinder;
import com.project.paradoxplatformer.view.javafx.FXMLHelper;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.lang3.tuple.Pair;

public class ViewSwitcher {

    private static Stage stage;

    private static FXMLHelper helper;

    public ViewSwitcher() {
        try {
            helper = new FXMLHelper();
        } catch (InvalidResourceException e) {
            e.printStackTrace();
        }
    }

    // Set the primary stage for switching views
    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    // Switch to a view based on PageIdentifier and optional parameter
    public static void switchTo(PageIdentifier id, String param) throws InvalidResourceException {
        Parent root = helper.mapper().apply(id).map(Pair::getKey).get();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
