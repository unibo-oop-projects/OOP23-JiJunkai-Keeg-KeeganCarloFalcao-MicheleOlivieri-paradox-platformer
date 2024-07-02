package com.project.paradoxplatformer.controller.deserialization;

import com.project.paradoxplatformer.controller.deserialization.dtos.ColorDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;

public interface DeserializerFactory {
    
    JsonDeserializer<LevelDTO> levelDeserialzer();

    JsonDeserializer<ColorDTO> colorDeserialzer();
}
