package com.project.paradoxplatformer.model.mappings;

import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;

public interface EntityDataMapper<T> {
    T map(GameDTO gamDto);
}
