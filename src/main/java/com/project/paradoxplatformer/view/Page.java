package com.project.paradoxplatformer.view;

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
    void create(T param) throws Exception;

    /**
     * Returns a default implementation of the Page interface.
     * This default page displays a blank screen with a message indicating that
     * the page was not found.
     *
     * @return a default Page implementation
     */
    static Page<String> defaultPage() {
        return new Page<String>() {

            @Override
            public void create(final String param) throws Exception {
                // Display a message indicating that the page was not found
                System.out.println("Page not Found: showing a Blank Screen");
            }

            @Override
            public String toString() {
                return "DEFAULT PAGE";
            }

        };
    }
}
