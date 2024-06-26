package com.project.paradoxplatformer.controller.deserialization;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;

public class LevelDeserializer{
 
    private final String json;

    public LevelDeserializer(final String json) {
        this.json = json;
    }

    public LevelDTO deserialize() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        URL jsonURL = LevelDeserializer.class.getResource(this.json);
        Objects.requireNonNull(jsonURL);
        return mapper.readValue(json, LevelDTO.class);
    }

}
