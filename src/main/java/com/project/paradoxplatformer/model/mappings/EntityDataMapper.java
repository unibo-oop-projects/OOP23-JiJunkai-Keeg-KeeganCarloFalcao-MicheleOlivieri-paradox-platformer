package com.project.paradoxplatformer.model.mappings;

import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;

/**
 * A functional interface for mapping {@link GameDTO} objects to a specific
 * type.
 * 
 * @param <T> the type to which the {@link GameDTO} is mapped
 */
public interface EntityDataMapper<T> {

    /**
     * Maps a {@link GameDTO} object to an instance of type {@code T}.
     *
     * @param gameDto the {@link GameDTO} to be mapped
     * @return an instance of type {@code T} representing the mapped data
     */
    T map(GameDTO gameDto);
}
