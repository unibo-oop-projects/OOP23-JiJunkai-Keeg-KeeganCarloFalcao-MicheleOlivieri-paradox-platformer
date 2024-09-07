package com.project.paradoxplatformer.controller.deserialization;

import com.project.paradoxplatformer.controller.deserialization.dtos.ColorDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;

/**
 * A factory define a deserializer based on clients needs, it is actually an utility helping clients
 * to choose wich deserliaziation they want.
 */
public interface DeserializerFactory {

    /**
     * Defines a particular deserializer factory, which is {@code LevelDTO}.
     * @return {@code JsonDeserializer<LevelDTO>}
     */
    JsonDeserializer<LevelDTO> levelDeserialzer();

    /**
     * Defines a particular deserializer factory, which is {@code ColorDTO}.
     * @return {@code JsonDeserializer<ColorDTO>}
     */
    JsonDeserializer<ColorDTO> colorDeserialzer();
}
