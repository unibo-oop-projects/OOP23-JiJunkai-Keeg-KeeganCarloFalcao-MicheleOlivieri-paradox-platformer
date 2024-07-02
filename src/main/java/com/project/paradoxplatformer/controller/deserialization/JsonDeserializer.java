package com.project.paradoxplatformer.controller.deserialization;

import java.io.IOException;

@FunctionalInterface
public interface JsonDeserializer<T> {
    T deserialize(String json) throws IOException;
}
