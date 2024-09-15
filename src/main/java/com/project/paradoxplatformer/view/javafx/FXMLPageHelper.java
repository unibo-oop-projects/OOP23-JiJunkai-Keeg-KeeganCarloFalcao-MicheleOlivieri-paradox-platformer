package com.project.paradoxplatformer.view.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.ResourcesFinder;
import com.project.paradoxplatformer.view.manager.api.FXMLView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Helper class for managing FXML pages in a JavaFX application.
 * <p>
 * This class is responsible for mapping {@link PageIdentifier} values to their
 * corresponding FXML resources and loading these resources to obtain their
 * associated controllers.
 * </p>
 *
 * @param <P> The type of the controller associated with the FXML page
 */
public final class FXMLPageHelper<P> {

    private final Map<PageIdentifier, URL> fxmlPagesPairing;

    /**
     * Constructs an FXMLPageHelper and initializes the mapping of
     * {@link PageIdentifier} to their respective FXML resource URLs.
     *
     * @throws InvalidResourceException if an error occurs while finding FXML
     *                                  resources
     */
    public FXMLPageHelper() throws InvalidResourceException {
        this.fxmlPagesPairing = new EnumMap<>(Map.of(
                PageIdentifier.MENU, ResourcesFinder.getURL(FXMLView.MENU.getFileName()),
                PageIdentifier.GAME, ResourcesFinder.getURL(FXMLView.GAME.getFileName()),
                PageIdentifier.SETTINGS, ResourcesFinder.getURL(FXMLView.SETTINGS.getFileName())));
    }

    /**
     * Provides a function to map {@link PageIdentifier} to an {@link Optional}
     * containing
     * a {@link Pair} of {@link Parent} and the controller of type {@code P}.
     * <p>
     * The {@link Parent} represents the loaded FXML root element, and {@code P} is
     * the
     * associated controller.
     * </p>
     *
     * @return a function that maps a {@link PageIdentifier} to an {@link Optional}
     *         containing
     *         a {@link Pair} of {@link Parent} and controller
     */
    public Function<PageIdentifier, Optional<Pair<Parent, P>>> mapper() {
        return p -> Optional.ofNullable(this.fxmlPagesPairing.get(p))
                .map(FXMLLoader::new)
                .map(this::loadInput);
    }

    /**
     * Loads the FXML resource using the provided {@link FXMLLoader}.
     * <p>
     * This method loads the FXML file and retrieves its associated controller.
     * </p>
     *
     * @param loader the {@link FXMLLoader} used to load the FXML resource
     * @return a {@link Pair} containing the loaded {@link Parent} and the
     *         controller of type {@code P}
     * @throws IllegalStateException if an error occurs during loading
     */
    private Pair<Parent, P> loadInput(final FXMLLoader loader) {
        try {
            final Parent parent = loader.load();
            final P controller = loader.getController();
            return Pair.of(parent, controller);

        } catch (IOException e) {
            throw new IllegalStateException("Loading controller from FXML error encountered", e);
        }
    }
}
