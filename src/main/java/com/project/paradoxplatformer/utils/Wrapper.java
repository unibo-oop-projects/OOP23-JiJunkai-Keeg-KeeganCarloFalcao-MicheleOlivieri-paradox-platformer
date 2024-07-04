package com.project.paradoxplatformer.utils;

public interface Wrapper<I> {
    
    void wrap(I i);

    I unwrap();
}
