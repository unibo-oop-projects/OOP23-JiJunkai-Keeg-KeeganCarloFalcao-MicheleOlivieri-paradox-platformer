package com.project.paradoxplatformer.model.entity.dynamics.abstracts;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.geometries.vector.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

//REMINDER
//should extend horizonal and vertical merged abstract class
public abstract class AbstractControllableObject extends AbstractHorizontalObject implements ControllableObject {


    protected Vector2D verticalSpeed;
    
    private static final double POWER = 13;
    private static final double ANTI_GRAVITY = -POWER+1;
    private double grav = ANTI_GRAVITY;

    protected AbstractControllableObject(final Vector2D initDisplacement, final HorizonalStats stats) {
        super(stats.limit(), stats.delta());
        this.verticalSpeed = new Simple2DVector(0., 0.);
    }

    //should implement by abstract class
    @Override
    public void jump() {
        if(grav == ANTI_GRAVITY) {
            this.verticalSpeed = new Simple2DVector(0., POWER);
            grav = POWER;
        }
        
    }

    @Override
    public void fall() {
        if(grav > ANTI_GRAVITY && grav <= POWER) {
            this.verticalSpeed = new Simple2DVector(0., grav-=1);
        }else {
            this.verticalSpeed = Polar2DVector.nullVector();
            
        }
        
        
    }
    //end comment

    @Override
    public void getStats() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStats'");
    }
    
}
