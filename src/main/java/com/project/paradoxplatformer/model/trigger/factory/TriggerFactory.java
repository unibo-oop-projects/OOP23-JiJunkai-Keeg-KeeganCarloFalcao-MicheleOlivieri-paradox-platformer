package com.project.paradoxplatformer.model.trigger.factory;

import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.model.trigger.api.TriggerType;

public class TriggerFactory {
    public static Trigger createTrigger(TriggerType type) {
        switch (type) {
            case FLOOR:
                return new FloorTrigger();
            case BUTTON:
                return new ButtonTrigger();
            case WARP:
                return new WarpTrigger();
            default:
                throw new IllegalArgumentException("Unsupported trigger type");
        }
    }
}
