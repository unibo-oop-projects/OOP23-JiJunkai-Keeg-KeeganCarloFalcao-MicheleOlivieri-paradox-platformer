/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.project.paradoxplatformer.utils.effect;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;


public class SpringEffect extends AbstractEffect{

    public SpringEffect() {
    }

    @Override
    protected CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    @Override
    protected CompletableFuture<Void> applyToTarget(Optional<? extends CollidableGameObject> target) {
        return CompletableFuture.runAsync(() -> {
            target.ifPresent(t -> {
                if (t instanceof ControllableObject) {
                    ((ControllableObject) t).jump();
                }
            });
        //Puoi fare anche cosi
        // target.filter(ControllableObject.class::isInstance)
        //       .map(ControllableObject.class::cast)
        //       .ifPresent(ControllableObject::jump);
        });
    }
}
