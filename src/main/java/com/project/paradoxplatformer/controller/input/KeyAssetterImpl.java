package com.project.paradoxplatformer.controller.input;

import java.util.Set;

import com.project.paradoxplatformer.controller.input.api.InputTranslator;
import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.controller.input.api.KeyAssetter;

import java.util.Collections;
import java.util.HashSet;

public class KeyAssetterImpl<K> implements KeyAssetter<K>{

    private final Set<InputType> pool;
    private InputTranslator<K> translator;

    public KeyAssetterImpl(InputTranslator<K> translator) {
        this.pool = new HashSet<InputType>();
        this.translator = translator;
    }

    public KeyAssetterImpl(KeyAssetter<K> copy) {
        this.pool = new HashSet<>(copy.getUnmodifiablePool());
    }

    public Set<InputType> getUnmodifiablePool() {
        return Collections.unmodifiableSet(pool);
    }

    @Override
    public boolean remove(K e) {
        return this.pool.remove(translator.translate(e));
    }

    @Override
    public boolean add(K e) {
        return this.pool.add(translator.translate(e));
    }

}
