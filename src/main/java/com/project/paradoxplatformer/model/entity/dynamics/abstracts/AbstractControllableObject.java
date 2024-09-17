package com.project.paradoxplatformer.model.entity.dynamics.abstracts;

import com.project.paradoxplatformer.controller.games.GameEventListener;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.entity.dynamics.behavior.JumpBehavior;
import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

//REMINDER
//should extend horizonal and vertical merged abstract class
public abstract class AbstractControllableObject extends AbstractHorizontalObject implements ControllableObject {


    protected Vector2D verticalSpeed;
    protected JumpBehavior jumpBehavior;
    private GameEventListener gameEventListener;
    protected boolean isJumping;

    protected AbstractControllableObject(final int key, final Vector2D initDisplacement, final HorizonalStats stats) {
        super(key, stats.limit(), stats.delta());
        this.verticalSpeed = new Simple2DVector(0., 0.);
        isJumping = false;
    }

    //should implement by abstract class
    @Override
    public void jump() {
        jumpBehavior.jump().ifPresent(vector -> {
            this.verticalSpeed = vector;
        });
    }

    @Override
    public void fall() {
        this.verticalSpeed = this.jumpBehavior.fall();
    }
    
    @Override
    public void setJumpBehavior(JumpBehavior jb) {
        this.jumpBehavior = jb;
    }

    public void setGameEventListener(GameEventListener listener) {
        this.gameEventListener = listener;
        
    }

    @Override
    public void onCollision() {
        if (gameEventListener != null) {
            System.out.println("Player death event triggered.");
            gameEventListener.onPlayerDeath(); // Notifica l'evento al controller
        } else {
            System.out.println("No GameEventListener attached.");
        }
    }

    @Override
    public void stopFall() {
        // Resetta la velocità verticale a zero per fermare la caduta
        this.verticalSpeed = new Simple2DVector(0., 0.);
        
        // Imposta lo stato di non caduta nel comportamento di salto
        jumpBehavior.setFalling(false);  // Ferma la caduta
        jumpBehavior.resetGravity();     // Resetta la gravità
        
    }

}
