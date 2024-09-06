package com.project.paradoxplatformer.model.entity;

public interface MutableObject extends CollidableGameObject {

    public void updateState(final long dt);

    public void setKey(int key);

    public int getKey();
}
