package com.project.paradoxplatformer.model.world;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.model.GameModelData;
import com.project.paradoxplatformer.model.mappings.model.ModelMappingFactory;
import com.project.paradoxplatformer.model.mappings.model.ModelMappingFactoryImpl;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.model.world.api.WorldBuilder;
import com.project.paradoxplatformer.utils.collision.CollisionManager;
import com.project.paradoxplatformer.utils.effect.EffectHandler;
import com.project.paradoxplatformer.utils.geometries.Dimension;

import static java.util.function.Predicate.not;
                                                
public final class PlatfromModelData implements GameModelData {

    private final LevelDTO packedData;
	private final WorldBuilder worldBuilder;
	private World world;
	private final ModelMappingFactory modelFactory;

	public PlatfromModelData(final LevelDTO packedData) {
		this.packedData = packedData;
		this.modelFactory = new ModelMappingFactoryImpl();
		this.worldBuilder = new WordBuilderImpl();
	}

	// COULD BETTER PERFORM
	@Override
	public void init() {
		Optional.of(
			Arrays.stream(packedData.getGameDTOs())
				.map(GameDTO::getType)
				.anyMatch(Objects::isNull)
		)
		.filter(u -> !u)
		.orElseThrow(() -> new IllegalStateException("Attribute type of game DTO is undefined, could not map"));

<<<<<<< HEAD
		this.world = this.worldBuilder
			.addbounds(new Dimension(packedData.getWidth(), packedData.getHeight()))
			.addPlayer(
				modelFactory.playerToModel()
				.map(this.findGameDTOData("player")
				.stream()
				.findFirst()
				.orElseThrow())
			)
			.addObstacle(
				this.findGameDTOData("obstacle")
				.stream()
				.map(modelFactory.obstacleToModel()::map)
				.toList()
				.toArray(new Obstacle[0])
			)
			.addTrigger(
				this.findGameDTOData("trigger")
				.stream()
				.map(modelFactory.triggerToModel()::map)
				.toList()
				.toArray(new Trigger[0])
			)
			.build();
	}
=======
                this.world = this.worldBuilder
                                .addbounds(new Dimension(packedData.getWidth(), packedData.getHeight()))
                                .addPlayer(modelFactory.playerToModel().map(
                                                this.findGameDTOData("player")
                                                                .stream()
                                                                .findFirst()
                                                                .orElseThrow()))
                                .addObstacle(
                                                this.findGameDTOData("obstacle").stream()
                                                                .map(modelFactory.obstacleToModel()::map)
                                                                .toList()
                                                                .toArray(new Obstacle[0]))
                                .addTrigger(
                                                this.findGameDTOData("trigger").stream()
                                                                .map(modelFactory.triggerToModel()::map)
                                                                .toList()
                                                                .toArray(new Trigger[0]))
                                .addCollisionManager(new CollisionManager(EffectHandler.createDefaultEffectHandler()))
                                .build();
        }
>>>>>>> 69a6b16809851b27309902a3132daa6aac5318f0

	private Collection<GameDTO> findGameDTOData(final String attribute) {
			return Optional.of(
					Set.of(packedData.getGameDTOs())
						.stream()
						.filter(g -> g.getType().equals(attribute))
						.toList()
				)
				.filter(not(List::isEmpty))
				.orElseThrow(
					() -> new IllegalArgumentException(
									"attribute does not match any game dto type: " + attribute
					)
				);
	}

	/**
	 * Due to security reasons returning world must be defensive copy of itself
	 */
	@Override
	public World getWorld() {
		return new WorldImpl(this.world);
	}

}
