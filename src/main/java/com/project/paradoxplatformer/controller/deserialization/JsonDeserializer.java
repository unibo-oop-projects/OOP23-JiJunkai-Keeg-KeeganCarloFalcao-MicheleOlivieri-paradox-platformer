package com.project.paradoxplatformer.controller.deserialization;

import java.io.IOException;
import com.project.paradoxplatformer.utils.InvalidResourceException;

/**
 * This interface abstracts the concept of deserialization. It defines a method
 * to deserialize a JSON file specified by its path into an object of type
 * {@code T}.
 * <p>
 * Note: The provided string is expected to be a file path to the JSON file, not
 * the JSON content itself.
 * </p>
 *
 * @param <T> the type of the object that the JSON file will be deserialized
 *            into
 */
@FunctionalInterface
public interface JsonDeserializer<T> {

    /**
     * Deserializes a JSON file specified by its file path.
     * <p>
     * This method throws exceptions to handle various issues that may occur during
     * the deserialization process:
     * <ul>
     * <li>{@code IOException} if there is an error reading the file, such as
     * the file being corrupted or unreadable.</li>
     * <li>{@code InvalidResourceException} if the file path is invalid or the
     * file is not found.</li>
     * </ul>
     * </p>
     *
     * @param json the path to the JSON file to be deserialized
     * @return the deserialized object of type {@code T}
     * @throws IOException              if an error occurs while reading the file
     * @throws InvalidResourceException if the JSON file path is not found or is
     *                                  invalid
     */
    T deserialize(String json) throws IOException, InvalidResourceException;
}
