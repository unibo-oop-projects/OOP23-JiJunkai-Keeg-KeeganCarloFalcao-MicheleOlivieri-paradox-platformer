package com.project.paradoxplatformer.utils.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for obtaining a global logger instance.
 */
public final class GlobalLogger {

    private GlobalLogger() {
        // Private constructor to prevent instantiation
    }

    /**
     * Gets a logger instance for the given class.
     *
     * @param clazz the class for which to get a logger instance
     * @return the logger instance
     */
    public static Logger getLogger(final Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
