package com.project.paradoxplatformer.controller.deserialization;

import java.io.IOException;
import com.project.paradoxplatformer.utils.InvalidResourceException;

/**
 * Such Interface abstracts the concept of deserialization and based on a string, usually a json file, it returns 
 * the desererialized object
 * 
 * NOTE: Such interface must get the json from a file, the string serves for that, not to deserialize based on the string
 * content
 */
@FunctionalInterface
public interface JsonDeserializer<T> {
    /**
     * A function to deserialize a json file
     * @param json Must be the json file path
     * @return
     * @throws IOException in case there was an error occuring the reading of the file
     * @throws InvalidResourceException when path is not found
     */
    T deserialize(String json) throws IOException, InvalidResourceException;
}
