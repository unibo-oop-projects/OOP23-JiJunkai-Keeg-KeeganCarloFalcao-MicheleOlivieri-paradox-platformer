package com.project.paradoxplatformer.utils;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.project.paradoxplatformer.HelloApplication;

public final class ResourcesFinder {

    private static final List<String> fxmlFiles = List.of(
            "game-view.fxml");

    public static List<URL> FXMLfiles() throws InvalidResourceException {

        return fxmlFiles.stream().map(t -> {
            try {
                return getURL(t);
            } catch (InvalidResourceException e) {
                throw new RuntimeException(e.getMessage());
            }
        }).toList();
    }

    public static Map<String, InputStream> allImages() {

        return null;

    }

    public static URL getURL(String filePath) throws InvalidResourceException {
        return Optional.ofNullable(HelloApplication.class.getResource(filePath))
                .orElseThrow(() -> new InvalidResourceException(filePath));

    }
}
