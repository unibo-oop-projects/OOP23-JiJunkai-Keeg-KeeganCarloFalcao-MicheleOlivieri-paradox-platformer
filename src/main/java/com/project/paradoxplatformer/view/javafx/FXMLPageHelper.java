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
import com.project.paradoxplatformer.view.FXMLViews;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public final class FXMLPageHelper<P> {

    private final Map<PageIdentifier, URL> fxmlPagesPairing;

    public FXMLPageHelper() throws InvalidResourceException {
        this.fxmlPagesPairing = new EnumMap<>(Map.of(
                PageIdentifier.MENU, ResourcesFinder.getURL(FXMLViews.MENU.getFileName()),
                PageIdentifier.GAME, ResourcesFinder.getURL(FXMLViews.GAME.getFileName())
            )
        );
    }

    public Function<PageIdentifier, Optional<Pair<Parent, P>>> mapper() {
        return p -> Optional.ofNullable(this.fxmlPagesPairing.get(p))
                .map(FXMLLoader::new)
                .map(this::loadInput);

    }

    private Pair<Parent, P> loadInput(FXMLLoader loader) {
        try {
            final Parent parent = loader.load();
            final P controller = loader.getController();
            return Pair.of(parent, controller);

        } catch (IOException e) {
            throw new IllegalStateException("Loading controller from FXML error encountered ", e); //Wraping exception with short message and rethrowing
        }
    }

}
