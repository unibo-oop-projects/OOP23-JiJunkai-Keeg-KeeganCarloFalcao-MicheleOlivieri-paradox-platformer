package com.project.paradoxplatformer.view.renders;

@FunctionalInterface
public interface Component<T> {
    T unwrap();
}
