package com.project.paradoxplatformer.utils.collision;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;

public class CollisionObserver {

    private final Set<CollidableGameObject> currentlyColliding = new HashSet<>();

    // Register actions for when objects start colliding and when they stop
    // colliding
    public void observeCollisions(Set<CollidableGameObject> detectedCollisions,
            BiConsumer<CollidableGameObject, CollisionType> onEnter,
            BiConsumer<CollidableGameObject, CollisionType> onExit) {
        // Handle newly detected collisions (entering collisions)
        detectedCollisions.stream()
                .filter(obj -> !currentlyColliding.contains(obj))
                .forEach(obj -> {
                    onEnter.accept(obj, obj.getCollisionType()); // Call the onEnter action
                    currentlyColliding.add(obj); // Add to the currently colliding set
                });

        // Handle objects that are no longer colliding (exiting collisions)
        currentlyColliding.stream()
                .filter(obj -> !detectedCollisions.contains(obj))
                .forEach(obj -> {
                    onExit.accept(obj, obj.getCollisionType()); // Call the onExit action
                });

        // Update the currently colliding set to match the detected collisions
        currentlyColliding.retainAll(detectedCollisions);
    }
}
