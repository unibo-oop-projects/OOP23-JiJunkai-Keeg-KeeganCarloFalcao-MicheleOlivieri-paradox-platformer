package com.project.paradoxplatformer.model.world;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.model.world.api.WorldBuilder;
import com.project.paradoxplatformer.utils.geometries.Dimension;

public class PlatfromModelData implements ModelData {

    private final LevelDTO packedData;

    public PlatfromModelData(final LevelDTO packedData) {
        this.packedData = packedData;
    }

    @Override
    public World init() {
        WorldBuilder lv = new WordBuilderImpl()
            .addbounds(new Dimension(packedData.getWidth(), packedData.getHeight()))
            .addPlayer(DataMapper.playerToModel().apply(
                this.findGameDTOData("player")
                    .stream()
                    .findFirst()
                    .orElseThrow()§
                )
            )
            .addObstacle(DataMapper.obstacleToModel().apply(
                this.findGameDTOData("obstac")
            )

        return null;
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
    
}
