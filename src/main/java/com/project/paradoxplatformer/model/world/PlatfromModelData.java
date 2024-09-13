package com.project.paradoxplatformer.model.world;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.model.GameModelData;
import com.project.paradoxplatformer.model.mappings.model.ModelMappingFactory;
import com.project.paradoxplatformer.model.mappings.model.ModelMappingFactoryImpl;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.model.world.api.WorldBuilder;
import com.project.paradoxplatformer.utils.geometries.Dimension;

import static java.util.function.Predicate.not;

public final class PlatfromModelData implements GameModelData {

	private final LevelDTO packedData;
	private WorldBuilder worldBuilder;
	private World world;
	private final ModelMappingFactory modelFactory;

	public PlatfromModelData(final LevelDTO packedData) {
		this.packedData = packedData;
		this.modelFactory = new ModelMappingFactoryImpl();
		this.worldBuilder = new WordBuilderImpl();
	}

	@Override
	public void init() {
		Optional.of(
				Arrays.stream(packedData.getGameDTOs())
						.map(GameDTO::getType)
						.anyMatch(Objects::isNull))
				.filter(u -> !u)
				.orElseThrow(() -> new IllegalStateException("Attribute type of game DTO is undefined, could not map"));

		PlayerModel player = modelFactory.playerToModel().map(
				this.findGameDTOData("player")
						.stream()
						.findFirst()
						.orElseThrow());

		Obstacle[] obstacles = this.findGameDTOData("obstacle").stream()
				.map(modelFactory.obstacleToModel()::map)
				.toList()
				.toArray(new Obstacle[0]);

		Trigger[] triggers = this.findGameDTOData("trigger").stream()
				.map(modelFactory.triggerToModel()::map)
				.toList()
				.toArray(new Trigger[0]);

		this.world = this.worldBuilder
				.addbounds(new Dimension(packedData.getWidth(), packedData.getHeight()))
				.addPlayer(player)
				.addObstacle(obstacles)
				.addTrigger(triggers)
				.build();
	}

	private Collection<GameDTO> findGameDTOData(final String attribute) {
		return Optional.of(
				List.of(packedData.getGameDTOs())
						.stream()
						.filter(g -> g.getType().equals(attribute))
						.toList())
				.filter(not(List::isEmpty))
				.orElseThrow(
						() -> new IllegalArgumentException(
								"attribute does not match any game dto type: " + attribute));
	}

	/**
	 * Due to security reasons returning world must be defensive copy of itself
	 */
	@Override
	public World getWorld() {
		return new WorldImpl(this.world);
	}

	@Override
	public void rebuild() {
		this.worldBuilder = new WordBuilderImpl();
	}

	@Override
	public void actionOnWorld(Consumer<World> action) {
		action.accept(this.world);
	}

}
