package com.project.paradoxplatformer.model.trigger;

import java.util.HashMap;
import java.util.Map;

import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.model.trigger.api.TriggerType;
import com.project.paradoxplatformer.model.trigger.factory.TriggerFactory;

public class TriggerManager {
    private Map<String, Trigger> triggers;

    public TriggerManager() {
        triggers = new HashMap<>();
    }

    public void addTrigger(String triggerId, TriggerType type) {
        Trigger trigger = TriggerFactory.createTrigger(type);
        triggers.put(triggerId, trigger);
    }

    public void activateTrigger(String triggerId) {
        Trigger trigger = triggers.get(triggerId);
        if (trigger != null) {
            trigger.activate();
        } else {
            System.out.println("Trigger with ID " + triggerId + " not found");
        }
    }
}