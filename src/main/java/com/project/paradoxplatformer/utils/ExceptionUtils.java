package com.project.paradoxplatformer.utils;

import java.util.Optional;

public final class ExceptionUtils {

    private ExceptionUtils() {
    }

    public static String advacendDisplay(final Exception ex) {
        return Optional.ofNullable(ex.getCause())
            .map(Throwable::getClass)
            .map(Class::getSimpleName)
            .orElse(ex.getClass().getSimpleName()) + " \nRaised → "
            + (
                Optional.ofNullable(ex.getMessage())
                .map(msg ->
                    Optional.ofNullable(ex.getCause())
                    .map(Throwable::getMessage)
                    .or(() ->
                        Optional.ofNullable(ex.getCause())
                            .filter(RuntimeException.class::isInstance)
                            .map(RuntimeException.class::cast)
                            .map(RuntimeException::getMessage)
                    )
                    .map(msg::concat)
                    .orElse(msg)
                ).orElse("[No error message available]")
            );
    }

    public static String simpleDisplay(final Exception ex) {
        return ex.getMessage() + "\n";
    }

    public static String basicDisplay() {
        return "Error encounterd\n";
    }
}
