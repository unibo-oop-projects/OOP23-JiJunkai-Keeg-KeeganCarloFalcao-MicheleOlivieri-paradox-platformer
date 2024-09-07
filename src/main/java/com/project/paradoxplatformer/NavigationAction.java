package com.project.paradoxplatformer;

import com.project.paradoxplatformer.utils.InvalidResourceException;

/**
 * A functional interface for defining navigation actions.
 * 
 * <p>
 * Provides a single method, {@code navigate()}, for performing navigation
 * between
 * views or screens. It can throw an {@link InvalidResourceException} if
 * navigation
 * fails due to invalid resources.
 * 
 * 
 */
@FunctionalInterface
public interface NavigationAction {

    /**
     * Executes the navigation action.
     * 
     * <p>
     * May throw an {@link InvalidResourceException} if navigation fails due to
     * missing or invalid resources.
     * 
     * @throws InvalidResourceException if an error occurs during navigation.
     */
    void navigate() throws InvalidResourceException;
}
