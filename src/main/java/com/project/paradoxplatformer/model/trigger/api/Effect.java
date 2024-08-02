package com.project.paradoxplatformer.model.trigger.api;

import java.util.Optional;;

public interface Effect {
    void apply(Optional<Object> target);
}