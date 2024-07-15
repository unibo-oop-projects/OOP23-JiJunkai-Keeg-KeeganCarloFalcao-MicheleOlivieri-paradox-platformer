package com.project.paradoxplatformer.utils;

public final class InvalidResourceException extends Exception{

    private static final String DEFAULT_MESSAGE = "File does not exist: ";

    public InvalidResourceException(String file) {
        super(DEFAULT_MESSAGE + file);
    }

    public InvalidResourceException(String file, Throwable e) {
        super(DEFAULT_MESSAGE + file, e);
    }

}
