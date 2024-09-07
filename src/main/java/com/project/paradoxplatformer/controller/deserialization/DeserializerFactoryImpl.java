package com.project.paradoxplatformer.controller.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.paradoxplatformer.controller.deserialization.dtos.ColorDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.utils.ResourcesFinder;

/**
 * a basic jackson deserializer factory implementation.
 * 
 * @see {@link DeserializerFactory}
 */
public class DeserializerFactoryImpl implements DeserializerFactory {

    /**
     * an empty constructor.
     */
    public DeserializerFactoryImpl() {
    }

    /**
     * Template method to deserialize using a jackson deserializer based on a class
     * name.
     * 
     * @param <D>   type of output deserializer
     * @param clazz class type, helps the jackson ds to handle injection
     * @return a jsondeserializer interface
     */
    private <D> JsonDeserializer<D> genericJackson(final Class<D> clazz) {
        return json -> new ObjectMapper().readValue(ResourcesFinder.getURL(json), clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonDeserializer<LevelDTO> levelDeserialzer() {
        return this.genericJackson(LevelDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonDeserializer<ColorDTO> colorDeserialzer() {
        return this.genericJackson(ColorDTO.class);
    }
}
