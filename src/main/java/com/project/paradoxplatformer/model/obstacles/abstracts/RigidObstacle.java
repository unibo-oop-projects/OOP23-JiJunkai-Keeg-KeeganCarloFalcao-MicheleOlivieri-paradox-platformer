package com.project.paradoxplatformer.model.obstacles.abstracts;

import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public interface RigidObstacle {

    Vector2D leftFace();

    Vector2D rightFace();

    Vector2D bottomFace();

    Vector2D upperFace();


}
