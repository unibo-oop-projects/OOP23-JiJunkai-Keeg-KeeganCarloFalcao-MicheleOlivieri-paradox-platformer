package com.project.paradoxplatformer.controller.deserialization;

import java.io.IOException;

import com.project.paradoxplatformer.utils.InvalidResourceException;

@FunctionalInterface
public interface JsonDeserializer<T> {
    T deserialize(String json) throws IOException, InvalidResourceException;
}
