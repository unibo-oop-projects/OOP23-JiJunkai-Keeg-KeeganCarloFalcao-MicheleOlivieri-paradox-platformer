package com.project.paradoxplatformer.utils;

import java.util.Objects;
import java.util.Random;


public final class SecureWrapper<T>{

    private final T value;
    private boolean isLocked;
    private int securedValue;

    private SecureWrapper(T i) {
        Objects.requireNonNull(i);
        this.value = i;
    }

    public static <T> SecureWrapper<T> of(T value) {
        return new SecureWrapper<>(value);
    }

    public T get() {
        if(this.isLocked) {
            throw new IllegalStateException("Cannot retrive value cause it is secured");    
        }
        return this.value;
    }

    public void secure(Random seedRand) {
        if(!this.isLocked) {
            this.isLocked = true;
            securedValue = seedRand.nextInt();
        } else{
            throw new IllegalStateException("Cannot secure the wrapper, is already locked");
        }
        
    }
    
    public void release(Random seedRand) {
        if(this.isLocked) {
            if(seedRand.nextInt() == this.securedValue) {
                this.isLocked = false;
            }
        } else {
            throw new IllegalStateException("Cannot realese cause wrapper is not secured");
        }
        
    }
}
