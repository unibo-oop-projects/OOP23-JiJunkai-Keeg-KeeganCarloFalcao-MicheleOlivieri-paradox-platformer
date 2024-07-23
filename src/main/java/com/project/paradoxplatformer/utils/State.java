package com.project.paradoxplatformer.utils;

import java.util.function.Consumer;

public interface State<T> {
    
    State<T> copyOf(T newState);

    State<T> onSubscribe(Consumer<T> action);

    void subscribe();
    
    T getValue();

    default State<T> updateState(T newState) {
        if(this.getValue().equals(newState)){
            return this;
        } else {
            return this.copyOf(newState);
        }
    }
    
}
