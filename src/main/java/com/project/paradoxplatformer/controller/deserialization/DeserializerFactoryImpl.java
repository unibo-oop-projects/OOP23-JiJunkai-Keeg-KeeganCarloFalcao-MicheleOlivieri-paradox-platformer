package com.project.paradoxplatformer.controller.deserialization;

import java.net.URL;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.paradoxplatformer.ResourcesFinder;
import com.project.paradoxplatformer.controller.deserialization.dtos.ColorDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;

public class DeserializerFactoryImpl implements DeserializerFactory {


    public DeserializerFactoryImpl() {}

    private <D> JsonDeserializer<D> genericJackson(final Class<D> clazz) {
        return json -> {
            ObjectMapper mapper = new ObjectMapper();
            URL jsonURL = ResourcesFinder.getURL(json);
            if (Objects.isNull(jsonURL)) {
                System.out.println("ERRORR");
                throw new IllegalArgumentException();
            }
            
            return mapper.readValue(jsonURL, clazz);
        };
    }

    @Override
    public JsonDeserializer<LevelDTO> levelDeserialzer() {
        return this.genericJackson(LevelDTO.class);
    }

    @Override
    public JsonDeserializer<ColorDTO> colorDeserialzer() {
        return this.genericJackson(ColorDTO.class);
    }
    
}
