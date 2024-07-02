package com.project.paradoxplatformer.model.world;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.model.obstacles.api.Obstacle;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.model.world.api.WorldBuilder;
import com.project.paradoxplatformer.model.world.mappings.model.ModelMappingFactory;
import com.project.paradoxplatformer.model.world.mappings.model.ModelMappingFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.Dimension;

public class PlatfromModelData implements ModelData {

    private final LevelDTO packedData;
    private final WorldBuilder worldBuilder;
    private World world;
    private final ModelMappingFactory modelFactory;

    public PlatfromModelData(final LevelDTO packedData) {
        this.packedData = packedData;
        this.modelFactory = new ModelMappingFactoryImpl();
        this.worldBuilder = new WordBuilderImpl();
    }

    @Override
    public LevelDTO getPackedData() {
        return this.packedData;
    }

    @Override
    public void init() {
        this.world = this.worldBuilder
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
            .build();
    }

    private Collection<GameDTO> findGameDTOData(final String attribute) {
        return Set.of(packedData.getGameDTOs()).stream()
            .filter(g-> g.getType().equals(attribute))
            .toList();
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stop'");
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }

    @Override
    public World getWorld() {
        return this.world;
    }
    
}
