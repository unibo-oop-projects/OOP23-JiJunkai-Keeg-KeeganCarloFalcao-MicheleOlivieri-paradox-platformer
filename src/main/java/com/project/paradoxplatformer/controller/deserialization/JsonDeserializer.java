package com.project.paradoxplatformer.controller.deserialization;

import java.io.IOException;
import com.project.paradoxplatformer.utils.InvalidResourceException;

/**
 * Such Interface abstracts the concept of deserialization and based on a string, usually a json file, it returns 
 * the desererialized object.
 * 
 * NOTE: Such interface must get the json from a file, the string serves for that, not to deserialize based on the string
 * content
 */
@FunctionalInterface
public interface JsonDeserializer<T> {
    /**
     * A function to deserialize a json file.
     * <p>Note that it prevently throws such exceptions. Such rigid exception handling have a reason:
     * in fact every deserializer must retrive the file path in resource folder 
     * (so it might not be there due to external dependecies)
     * and then read its content (file might not be a json, or OS was unable to read its content e.g file corrupted)</p>
     * @param json Must be the json file path
     * @return
     * @throws IOException in case there was an error occuring the reading of the file
     * @throws InvalidResourceException when path is not found
     */
    T deserialize(String json) throws IOException, InvalidResourceException;
}
