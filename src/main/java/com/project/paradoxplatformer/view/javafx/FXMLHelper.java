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
import com.project.paradoxplatformer.view.Page;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public final class FXMLHelper {

    private Map<PageIdentifier, URL> fxmlPagesPairing;

    public FXMLHelper() throws InvalidResourceException {
        this.fxmlPagesPairing = new EnumMap<>(Map.of(
                PageIdentifier.GAME, ResourcesFinder.getURL("hello-view.fxml"),
                PageIdentifier.MENU, ResourcesFinder.getURL("new-level-view.fxml")));
    }

    public Function<PageIdentifier, Optional<Pair<Parent, Page<String>>>> mapper() {
        return p -> Optional.ofNullable(this.fxmlPagesPairing.get(p))
                .map(FXMLLoader::new)
                .map(this::loadInput);

    }

    private Pair<Parent, Page<String>> loadInput(FXMLLoader loader) {
        try {
            Parent parent = loader.load();
            Page<String> controller = loader.getController();
            return Pair.of(parent, controller);

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
