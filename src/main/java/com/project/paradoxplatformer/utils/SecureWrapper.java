package com.project.paradoxplatformer.utils;

import java.util.Objects;
import java.util.Random;

/**
 * A secure wrapper for an object that can be locked and secured to prevent
 * access to its value.
 *
 * @param <T> the type of the wrapped value
 */
public final class SecureWrapper<T> {

    private final T value;
    private boolean isLocked;
    private int securedValue;

    /**
     * Constructs a SecureWrapper with the specified value.
     *
     * @param value the value to be wrapped
     * @throws NullPointerException if the value is null
     */
    private SecureWrapper(final T value) {
        Objects.requireNonNull(value, "Value cannot be null");
        this.value = value;
    }

    /**
     * Creates a new SecureWrapper with the specified value.
     *
     * @param value the value to be wrapped
     * @param <T>   the type of the wrapped value
     * @return a SecureWrapper containing the specified value
     */
    public static <T> SecureWrapper<T> of(final T value) {
        return new SecureWrapper<>(value);
    }

    /**
     * Retrieves the wrapped value if it is not secured.
     *
     * @return the wrapped value
     * @throws IllegalStateException if the value is secured
     */
    public T get() {
        if (this.isLocked) {
            throw new IllegalStateException("Cannot retrieve value because it is secured");
        }
        return this.value;
    }

    /**
     * Secures the wrapper by generating a secured value.
     *
     * @param seedRand the random number generator used to generate the secured
     *                 value
     * @throws IllegalStateException if the wrapper is already secured
     */
    public void secure(final Random seedRand) {
        if (!this.isLocked) {
            this.isLocked = true;
            this.securedValue = seedRand.nextInt();
        } else {
            throw new IllegalStateException("Cannot secure the wrapper, it is already locked");
        }
    }

    /**
     * Releases the wrapper if the provided random number matches the secured value.
     *
     * @param seedRand the random number generator used to validate the secured
     *                 value
     * @throws IllegalStateException if the wrapper is not secured or if the value
     *                               does not match
     */
    public void release(final Random seedRand) {
        if (this.isLocked) {
            if (seedRand.nextInt() == this.securedValue) {
                this.isLocked = false;
            } else {
                throw new IllegalStateException("Cannot release because the provided value does not match");
            }
        } else {
            throw new IllegalStateException("Cannot release because the wrapper is not secured");
        }
    }
}
