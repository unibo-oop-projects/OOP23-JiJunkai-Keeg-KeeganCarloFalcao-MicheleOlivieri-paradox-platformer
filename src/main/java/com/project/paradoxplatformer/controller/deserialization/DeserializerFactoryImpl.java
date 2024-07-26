package com.project.paradoxplatformer.controller.deserialization;

import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.paradoxplatformer.controller.deserialization.dtos.ColorDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.utils.ResourcesFinder;

public class DeserializerFactoryImpl implements DeserializerFactory {

    public DeserializerFactoryImpl() {}

    private <D> JsonDeserializer<D> genericJackson(final Class<D> clazz){
        return json -> {
            final ObjectMapper mapper = new ObjectMapper();
            final URL jsonURL = ResourcesFinder.getURL(json);
            return mapper.readValue(jsonURL, clazz);
        };
    }

    @Override
    public JsonDeserializer<LevelDTO> levelDeserialzer() {
        return this.genericJackson(LevelDTO.class);
    }

    @Override
    public JsonDeserializer<ColorDTO> colorDeserialzer(){
        return this.genericJackson(ColorDTO.class);
    }
    
}
