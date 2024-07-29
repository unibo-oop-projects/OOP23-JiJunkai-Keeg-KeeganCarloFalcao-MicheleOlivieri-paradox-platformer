package com.project.paradoxplatformer.controller;

import java.util.Optional;

public final class ExceptionUtils {

    private ExceptionUtils() {

    }

    public static String advacendDisplay(final Exception ex) {
        return Optional.ofNullable(ex.getCause())
            .map(Throwable::getClass)
            .map(Class::getSimpleName)
            .orElse(ex.getClass().getSimpleName()) + " \nRaised → "
            + Optional.ofNullable(ex.getMessage())
            .map(m ->
                Optional.ofNullable(ex.getCause())
                .map(Throwable::getMessage)
                .or(() ->
                    Optional.ofNullable(ex.getCause())
                        .filter(RuntimeException.class::isInstance)
                        .map(RuntimeException.class::cast)
                        .map(RuntimeException::getMessage)
                )
                .map(m::concat)
                .orElse(m)
            ).orElse("[No error message avaible]");
    }

    public static String simpleDisplay(final Exception ex) {
        return ex.getMessage() + "\n";
    }

    public static String basicDisplay() {
        return "Error encounterd\n";
    }
}
