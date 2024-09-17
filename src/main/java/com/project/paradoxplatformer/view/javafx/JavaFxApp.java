package com.project.paradoxplatformer.view.javafx;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.utils.ExceptionUtils;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.ResourcesFinder;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.Page;
import com.project.paradoxplatformer.view.legacy.ViewFramework;
import com.project.paradoxplatformer.view.manager.ViewManager;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX application class for managing the application's main window and
 * scenes.
 */
public class JavaFxApp extends Application implements ViewManager {

    private static Scene scene;
    private static Stage stage;
    private static FXMLPageHelper<Page<Level>> helper;
    private boolean created;
    private static String staticTitle = "";
    private static CountDownLatch latch;

    private static final class LazyHolder {
        private static final ViewManager SINGLETON = new JavaFxApp();
    }

    /**
     * Returns the singleton instance of the ViewManager.
     * 
     * @return the singleton instance of the ViewManager
     */
    public static ViewManager getInstance() {
        return LazyHolder.SINGLETON;
    }

    /**
     * Creates a new Scene with the given root node.
     * 
     * @param root the root node for the scene
     * @return the newly created Scene
     */
    public static Scene createScene(final Parent root) {
        return new Scene(root);
    }

    /**
     * Default constructor.
     */
    public JavaFxApp() {
    }

    /**
     * Initializes the application.
     * 
     * <p>
     * This method is called by the JavaFX runtime to initialize the application.
     * It should be called before {@link #start(Stage)}.
     * </p>
     */
    @Override
    public void init() {
        this.created = true;
    }

    /**
     * Starts the JavaFX application and initializes the primary stage.
     * 
     * @param primeStage the primary stage for this application
     * @throws IOException if there is an issue loading the FXML pages
     */
    @Override
    @SuppressFBWarnings(value = { "ST" }, justification = "Made for a better structure.")
    public void start(final Stage primeStage) throws IOException {
        if (!created) {
            throw new IllegalStateException("Cannot create application, Security reasons");
        }
        stage = primeStage;
        stage.setOnCloseRequest(e -> this.exit());
        try {
            helper = new FXMLPageHelper<>();
        } catch (InvalidResourceException | RuntimeException ex) {
            this.displayError(ExceptionUtils.advancedDisplay(ex));
            this.safeError();
        }
        stage.setTitle(staticTitle);
        this.setInitialScene();
        stage.show();
        Optional.ofNullable(latch).ifPresent(CountDownLatch::countDown);
    }

    /**
     * Creates and launches the application with the given title.
     * 
     * @param appTitle the title of the application window
     */
    @Override
    @SuppressFBWarnings(value = { "ST" }, justification = "Made for a better structure.")
    public void create(final String appTitle) {
        staticTitle = appTitle;
        JavaFxApp.launch();
    }

    /**
     * Creates and launches the application with the given title and CountDownLatch.
     * 
     * @param referedLatch the CountDownLatch to signal when the application is
     *                     ready
     * @param appTitle     the title of the application window
     */
    @Override
    @SuppressFBWarnings(value = { "ST" }, justification = "Made for a better structure.")
    public void create(final CountDownLatch referedLatch, final String appTitle) {
        latch = referedLatch;
        this.create(appTitle);
    }

    /**
     * Switches to a new page based on the provided identifier.
     * 
     * @param id the identifier of the page to switch to
     * @return the new Page that is displayed
     * @throws IllegalStateException if called from a non-JavaFX thread
     */
    @Override
    public Page<Level> switchPage(final PageIdentifier id) {
        if (Platform.isFxApplicationThread()) {
            System.out.println("In SWITCH PANE FUNCTION");
            System.out.println("[CURRENT ID]: " + id);

            var entry = helper.mapper().apply(id);
            scene.setRoot(
                    entry.map(Pair::getKey)
                            .orElse(ViewFramework.javaFxFactory().blankPage()));
            stage.sizeToScene();

            System.out.println("[PANE]: " + entry.map(Pair::getValue).orElse(Page.defaultPage()));
            return entry.map(Pair::getValue).orElse(Page.defaultPage());
        }
        throw new IllegalStateException("Not in FX Thread");
    }

    /**
     * Displays an informational message dialog with the given title, header, and
     * content.
     * 
     * @param title   the title of the message dialog
     * @param header  the header text of the message dialog
     * @param content the content text of the message dialog
     */
    @Override
    public void displayMessage(final String title, final String header, final String content) {
        final Alert alert = new Alert(AlertType.NONE);
        this.setAndShowAlert(alert, AlertType.INFORMATION, title, header, content);
        this.runOnAppThread(alert::showAndWait);
    }

    /**
     * Displays an error dialog with the given content.
     * 
     * @param content the content text of the error dialog
     */
    @Override
    public void displayError(final String content) {
        var al = new Alert(AlertType.ERROR, content);
        DialogPane errorPane;
        try {
            errorPane = new FXMLLoader(ResourcesFinder.getURL("diag-pane.fxml")).load();
            al.setDialogPane(errorPane);
            this.setDialoContent(content, errorPane);
        } catch (IOException | InvalidResourceException | ClassCastException e) {
            al.setHeaderText("Custom Alert failed, showing Default Alert");
            al.setContentText(content + "\n\nWhy custom alert failed to load? ¬"
                    + "\n" + ExceptionUtils.advancedDisplay(e));
        } finally {
            al.showAndWait();
        }
    }

    /**
     * Displays a confirmation dialog with the given header and closing content.
     * Exits the application if the user confirms.
     * 
     * @param header         the header text of the confirmation dialog
     * @param closingContent the content text of the confirmation dialog
     */
    @Override
    public void closeWithMessage(final String header, final String closingContent) {
        final Alert alert = new Alert(AlertType.NONE);
        this.setAndShowAlert(alert, AlertType.CONFIRMATION, "CLOSING", header, closingContent);
        alert.showAndWait().ifPresent(b -> this.exit());
    }

    /**
     * Exits the application safely by calling Platform.exit() and terminating the
     * JVM.
     */
    @Override
    public void safeError() {
        this.exit();
        Runtime.getRuntime().exit(0);
    }

    /**
     * Exits the application by calling Platform.exit().
     */
    @Override
    public void exit() {
        Platform.exit();
    }

    /**
     * Runs the provided Runnable on the JavaFX application thread.
     * 
     * @param runner the Runnable to run on the JavaFX application thread
     */
    @Override
    public void runOnAppThread(final Runnable runner) {
        Platform.runLater(runner);
    }

    private void setInitialScene() {
        final double resolution = 720;
        scene = new Scene(ViewFramework.javaFxFactory().loadingPage(), resolution * ASPECT_RATIO, resolution);
        stage.sizeToScene();
        stage.setScene(scene);

        System.out.println("Main View Size → " + new Dimension(scene.getWidth(), scene.getHeight()));
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
