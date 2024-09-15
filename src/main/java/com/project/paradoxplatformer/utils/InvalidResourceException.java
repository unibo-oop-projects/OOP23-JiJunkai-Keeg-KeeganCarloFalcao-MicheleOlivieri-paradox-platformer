package com.project.paradoxplatformer.utils;

/**
 * Exception thrown when a requested resource cannot be found.
 */
public final class InvalidResourceException extends Exception {

    private static final String DEFAULT_MESSAGE = "File does not exist: ";

    /**
     * Constructs a new InvalidResourceException with a detailed message.
     * 
     * @param file the path to the resource file that could not be found
     */
    public InvalidResourceException(final String file) {
        super(DEFAULT_MESSAGE + file);
    }

    /**
     * Constructs a new InvalidResourceException with a detailed message and a
     * cause.
     * 
     * @param file the path to the resource file that could not be found
     * @param e    the cause of the exception
     */
    public InvalidResourceException(final String file, final Throwable e) {
        super(DEFAULT_MESSAGE + file, e);
    }
}
