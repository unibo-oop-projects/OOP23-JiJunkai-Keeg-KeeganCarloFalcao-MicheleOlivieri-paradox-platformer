package com.project.paradoxplatformer.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DIContainer {
    private final Map<Class<?>, Supplier<?>> providers = new HashMap<>();

    public <T> void register(Class<T> type, Supplier<? extends T> provider) {
        providers.put(type, provider);
    }

    public <T> T resolve(Class<T> type) {
        Supplier<?> provider = providers.get(type);
        if (provider == null) {
            throw new IllegalStateException("No provider registered for " + type.getName());
        }
        return type.cast(provider.get());
    }
}
