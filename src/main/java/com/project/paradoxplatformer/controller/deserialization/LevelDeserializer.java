package com.project.paradoxplatformer.controller.deserialization;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.paradoxplatformer.MainApplication;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;

public class LevelDeserializer{
 
    private final String json;

    public LevelDeserializer(final String json) {
        this.json = json;
    }

    public LevelDTO deserialize() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        URL jsonURL = MainApplication.class.getResource(this.json);
        if (Objects.isNull(jsonURL)) {
            throw new IllegalArgumentException();
        }
        return mapper.readValue(jsonURL, LevelDTO.class);
    }

}
