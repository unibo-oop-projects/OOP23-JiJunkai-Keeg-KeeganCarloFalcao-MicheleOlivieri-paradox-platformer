package com.project.paradoxplatformer.utils.collision;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import java.util.List;

public class CollisionDetector {

    public static <T extends CollidableGameObject> boolean isColliding(T obj1, T obj2) {
        System.out.println(obj1);
        System.out.println(obj2);
        obj2.getPosition().x();
        return !(obj1.getPosition().x() + obj1.getDimension().width() <= obj2.getPosition().x() ||
                obj1.getPosition().x() >= obj2.getPosition().x() + obj2.getDimension().width() ||
                obj1.getPosition().y() + obj1.getDimension().height() <= obj2.getPosition().y() ||
                obj1.getPosition().y() >= obj2.getPosition().y() + obj2.getDimension().height());
    }

    public static <T extends CollidableGameObject> boolean hasCollision(T obj, List<T> collidableGameObjects) {
        return collidableGameObjects.stream()
                .anyMatch(other -> !other.equals(obj) && isColliding(obj, other));
    }
}