package com.project.paradoxplatformer.model.entity.dynamics.abstracts;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

//REMINDER
//should extend horizonal and vertical merged abstract class
public abstract class AbstractControllableObject extends AbstractHorizontalObject implements ControllableObject {


    protected AbstractControllableObject(Vector2D initDisplacement, HorizonalStats stats) {
        super(stats.limit(), stats.delta());
    }

    //should implement by abstract class
    @Override
    public void jump() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'jump'");
    }

    @Override
    public void fall() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fall'");
    }
    //end comment

    @Override
    public void getStats() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStats'");
    }
    
}
