package com.project.paradoxplatformer.utils.collision;

import com.project.paradoxplatformer.utils.collision.api.Collidable;

import java.util.List;

public class CollisionDetector {

    public static <T extends Collidable> boolean isColliding(T obj1, T obj2) {
        return !(obj1.getPosition().x() + obj1.getDimension().width() <= obj2.getPosition().x() ||
                obj1.getPosition().x() >= obj2.getPosition().x() + obj2.getDimension().width() ||
                obj1.getPosition().y() + obj1.getDimension().height() <= obj2.getPosition().y() ||
                obj1.getPosition().y() >= obj2.getPosition().y() + obj2.getDimension().height());
    }

    public static <T extends Collidable> boolean hasCollision(T obj, List<T> collidables) {
        return collidables.stream()
                .anyMatch(other -> !other.equals(obj) && isColliding(obj, other));
    }
}