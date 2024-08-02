package com.project.paradoxplatformer.utils.collision;

import com.project.paradoxplatformer.model.entity.GameObject;
import java.util.List;

public class CollisionDetector {

    public static <T extends GameObject> boolean isColliding(T obj1, T obj2) {
        return !(obj1.getPosition().x() + obj1.getDimension().width() <= obj2.getPosition().x() ||
                obj1.getPosition().x() >= obj2.getPosition().x() + obj2.getDimension().width() ||
                obj1.getPosition().y() + obj1.getDimension().height() <= obj2.getPosition().y() ||
                obj1.getPosition().y() >= obj2.getPosition().y() + obj2.getDimension().height());
    }

    public static <T extends GameObject> boolean hasCollision(T obj, List<T> gameObjects) {
        return gameObjects.stream()
                .anyMatch(other -> !other.equals(obj) && isColliding(obj, other));
    }
}