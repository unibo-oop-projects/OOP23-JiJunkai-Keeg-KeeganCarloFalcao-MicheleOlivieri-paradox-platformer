package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.world.vector.api.Vector2D;

public record TrajectoryInfo(Vector2D endpoint, long duration, TrasformType transfType ) {

}
