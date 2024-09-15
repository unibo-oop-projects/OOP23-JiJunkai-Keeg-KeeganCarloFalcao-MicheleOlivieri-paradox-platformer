package com.project.paradoxplatformer.utils;

import java.util.Optional;

/**
 * Utility class for handling exceptions and providing formatted exception
 * messages.
 */
public final class ExceptionUtils {

    // Private constructor to prevent instantiation
    private ExceptionUtils() {
    }

    /**
     * Provides an advanced display of the exception, including its cause and
     * message.
     *
     * @param ex the exception to be displayed
     * @return a string representation of the exception, including its cause and
     *         message
     */
    public static String advancedDisplay(final Exception ex) {
        return Optional.ofNullable(ex.getCause())
                .map(Throwable::getClass)
                .map(Class::getSimpleName)
                .orElse(ex.getClass().getSimpleName()) + " \nRaised → "
                + (Optional.ofNullable(ex.getMessage())
                        .map(msg -> Optional.ofNullable(ex.getCause())
                                .map(Throwable::getMessage)
                                .or(() -> Optional.ofNullable(ex.getCause())
                                        .filter(RuntimeException.class::isInstance)
                                        .map(RuntimeException.class::cast)
                                        .map(RuntimeException::getMessage))
                                .map(msg::concat)
                                .orElse(msg))
                        .orElse("[No error message available]"));
    }

    /**
     * Provides a simple display of the exception message.
     *
     * @param ex the exception to be displayed
     * @return a string representation of the exception message
     */
    public static String simpleDisplay(final Exception ex) {
        return ex.getMessage() + "\n";
    }

    /**
     * Provides a basic display message indicating an error has occurred.
     *
     * @return a string indicating that an error was encountered
     */
    public static String basicDisplay() {
        return "Error encountered\n";
    }
}