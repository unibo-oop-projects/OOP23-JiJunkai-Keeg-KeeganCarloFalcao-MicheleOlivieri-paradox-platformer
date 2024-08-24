package com.project.paradoxplatformer.utils.collision;

import java.util.Objects;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;

public class CollisionIdentifier {
    private final CollidableGameObject source;
    private final CollidableGameObject target;

    public CollisionIdentifier(CollidableGameObject source, CollidableGameObject target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CollisionIdentifier that = (CollisionIdentifier) o;
        return source.equals(that.source) && target.equals(that.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }
}
