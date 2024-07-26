package com.project.paradoxplatformer.model.world;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.model.mappings.model.ModelMappingFactory;
import com.project.paradoxplatformer.model.mappings.model.ModelMappingFactoryImpl;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.model.world.api.WorldBuilder;
import com.project.paradoxplatformer.utils.SecureWrapper;
import com.project.paradoxplatformer.utils.geometries.Dimension;

import static java.util.function.Predicate.not;

public  final class PlatfromModelData implements GameModelData {

    private final LevelDTO packedData;
    private final WorldBuilder worldBuilder;
    private SecureWrapper<World> world;
    private final ModelMappingFactory modelFactory;

    public PlatfromModelData(final LevelDTO packedData) {
        this.packedData = packedData;
        this.modelFactory = new ModelMappingFactoryImpl();
        this.worldBuilder = new WordBuilderImpl();
    }

    //COULD BETTER PERFORM
    @Override
    public void init() {
        Optional.of(
            Arrays.stream(packedData.getGameDTOs())
            .map(GameDTO::getType)
            .anyMatch(Objects::isNull)
        )
        .filter(u -> !u)
        .orElseThrow(() -> 
            new IllegalStateException("Attribute type of game DTO is undefined, could not map")
        );
        
        this.world = SecureWrapper.of(this.worldBuilder
            .addbounds(new Dimension(packedData.getWidth(), packedData.getHeight()))
            .addPlayer(modelFactory.playerToModel().map(
                this.findGameDTOData("player")
                    .stream()
                    .findFirst()
                    .orElseThrow()
                )
            )
            .addObstacle(
                this.findGameDTOData("obstacle").stream()
                    .map(modelFactory.obstacleToModel()::map)
                    .toList()
                    .toArray(new Obstacle[0])                
            )
            .build());
    }

    private Collection<GameDTO> findGameDTOData(final String attribute) {
        
        return Optional.of(Set.of(packedData.getGameDTOs()).stream()
            .filter(g-> g.getType().equals(attribute))
            .toList())
            .filter(not(List::isEmpty))
            .orElseThrow(() -> new IllegalArgumentException("attribute does not match any game dto type: " + attribute));
    }

    //GOTTA CHECK INIT HAS DONE
    //RETURNING AN MUTABLE MUST FIX
    @Override
    public World getWorld() {
        return this.world.get();
    }
    
}
