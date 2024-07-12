package com.project.paradoxplatformer.utils.geometries.modifiers;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.utils.geometries.interpolations.Interpolator;
import com.project.paradoxplatformer.utils.geometries.modifiers.api.Physics;
import com.project.paradoxplatformer.utils.geometries.vector.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

//IDEA THIS SHOULD BE A FACTORY OF MOVES
public class PhysicsEngine implements Physics {

    private static final double CLAMP_VALUE = 1.;
    //initally use protecte, dont know wether it needs on lower classes
    private double elapseTime;

    public PhysicsEngine() {
        this.elapseTime = 0.;
    }

    //deltatime is ambigous, dont know whether it needs to be put as contructor paramter or function neccessity
    //move paramaters are getting numerous
    @Override
    public Pair<Vector2D, Double> moveTo(final Vector2D start, final Vector2D end, final long duration, Interpolator<Vector2D> interpType, long dt) {
        this.elapseTime += dt;
        double y = elapseTime / (duration*1000.);
        
        //doubt, does duration express on nanosec or conventional?, needs a conversion if so
        double percentage = Math.min(y, CLAMP_VALUE);
        return Pair.of(interpType.lerp(start, end, percentage), percentage);
    }

    @Override
    public Vector2D step(final Vector2D start, final Vector2D end, final Interpolator<Vector2D> interpType, long dt) {
        return interpType.lerp(start, end, dt / 1000.);
    }

    //alternatives: 
    //doubts: return type of move should be void or resulting vector and so the field would be 
    //erased, making every move (left, right, jump, spin) affected by move function
    @Override
    public Vector2D stop() {
        this.elapseTime = 0.d;
        return Polar2DVector.nullVector();
    }

    

}
