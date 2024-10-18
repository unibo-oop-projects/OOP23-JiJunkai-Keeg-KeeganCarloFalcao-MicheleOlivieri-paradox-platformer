package com.project.paradoxplatformer.view.page;

import java.io.IOException;

import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.utils.InvalidResourceException;

/**
 * Interface representing a page in the application.
 * This interface provides a method to create or initialize the page
 * with a given parameter.
 *
 * @param <T> The type of parameter used to create or initialize the page.
 */
public interface Page<T> {
    /**
     * Creates or initializes the page with the specified parameter.
     *
     * @param param the parameter used to create or initialize the page
     * @throws Exception if an error occurs during the creation or initialization of
     *                   the page
     */
    void create(T param) throws Exception; // NOPMD ("it is necessary as it catches all type of exception")

    /**
     * Returns a default implementation of the Page interface.
     * This default page displays a blank screen with a message indicating that
     * the page was not found.
     *
     * @return a default Page implementation
     */
    static Page<Level> defaultPage() {
        return new Page<>() {

            @Override
            public void create(final Level param) throws IOException, InvalidResourceException {
                // Display a message indicating that the page was not found
                // System.out.println("Page not Found: showing a Blank Screen");
            }

            @Override
            public String toString() {
                return "DEFAULT PAGE";
            }

        };
    }
}
