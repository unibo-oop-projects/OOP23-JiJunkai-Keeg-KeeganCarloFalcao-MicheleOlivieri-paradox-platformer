package com.project.paradoxplatformer.utils;

import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

import com.project.paradoxplatformer.App;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Utility class for finding resources within the application's classpath.
 * Provides methods to retrieve resource URLs and input streams.
 */
public final class ResourcesFinder {

    /**
     * Json folder path.
     */
    public static final String JSON_FOLDER = "jsons/";

    /**
     * images folder path.
     */
    public static final String IMAGES_FOLDER = "images/";

    /**
     * fxml related folder path.
     */
    public static final String FXML_FOLDER = "fxmls/";

    // Private constructor to prevent instantiation
    private ResourcesFinder() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }

    /**
     * Retrieves the URL of the specified resource file.
     * 
     * @param filePath the path to the resource file
     * @return the URL of the resource
     * @throws InvalidResourceException if the resource cannot be found
     */
    public static URL getURL(final String filePath) throws InvalidResourceException {
        return Optional.ofNullable(App.class.getResource(filePath))
                .orElseThrow(() -> new InvalidResourceException(filePath));
    }

    /**
     * Retrieves an input stream for the specified resource file.
     * 
     * @param filePath the path to the resource file
     * @return an input stream for the resource
     * @throws InvalidResourceException if the resource cannot be found
     */
    @SuppressFBWarnings(
            value = "OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE", 
            justification = "This needs to be managed by User. "
            + "It is the caller's responsibility to close the stream. "
            + "The method itself only provides access to the stream and does not handle its lifecycle."
        )
    public static InputStream getInputStream(final String filePath) throws InvalidResourceException {
        return Optional.ofNullable(App.class.getResourceAsStream(filePath))
                .orElseThrow(() -> new InvalidResourceException(filePath));
    }
}
