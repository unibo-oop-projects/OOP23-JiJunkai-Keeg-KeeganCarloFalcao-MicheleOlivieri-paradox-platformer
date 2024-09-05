package com.project.paradoxplatformer;

import com.project.paradoxplatformer.utils.InvalidResourceException;

/**
 * A functional interface representing a navigation action.
 * 
 * <p>
 * This interface is designed to encapsulate the logic for navigating between
 * different views or screens within the application. It defines a single
 * method, {@code navigate()}, which is responsible for performing the actual
 * navigation and can handle potential errors by throwing an
 * {@link InvalidResourceException}.
 * 
 * <p>
 * Using this functional interface allows for clean, lambda-based navigation
 * logic, making the code more modular and concise.
 * 
 * <p>
 * Typical usage involves binding UI elements (such as buttons) to specific
 * navigation actions using lambda expressions or method references, allowing
 * for a clear separation of concerns between UI event handling and navigation
 * logic.
 * 
 * <p>
 * Example:
 * 
 * <pre>{@code
 * EventBinder.bindLevelCircle(levelOneButton, () -> handleNavigation(viewNavigator::goToLevelOne));
 * }</pre>
 */
@FunctionalInterface
public interface NavigationAction {

    /**
     * Executes the navigation action.
     * 
     * <p>
     * This method is responsible for performing the logic necessary to
     * navigate to a new view or state within the application. It may throw an
     * {@link InvalidResourceException} if the navigation target is invalid or
     * cannot be located (e.g., missing FXML files or incorrect paths).
     * 
     * @throws InvalidResourceException if an error occurs while performing the
     *                                  navigation action (e.g., missing resource).
     */
    void navigate() throws InvalidResourceException;
}
