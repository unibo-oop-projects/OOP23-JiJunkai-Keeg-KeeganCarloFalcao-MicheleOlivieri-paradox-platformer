package com.project.paradoxplatformer.utils;

import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

import com.project.paradoxplatformer.HelloApplication;

public final class ResourcesFinder {

    public static URL getURL(String filePath) throws InvalidResourceException {
        return Optional.ofNullable(HelloApplication.class.getResource(filePath))
                .orElseThrow(() -> new InvalidResourceException(filePath));

    }

    public static InputStream getInputStream(String filePath) throws InvalidResourceException {
        return Optional.ofNullable(HelloApplication.class.getResourceAsStream(filePath))
                .orElseThrow(() -> new InvalidResourceException(filePath));
    }
}
