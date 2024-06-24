package com.project.paradoxplatformer.utils.geometries.modifiers;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.utils.geometries.interpolations.Interpolator;
import com.project.paradoxplatformer.utils.geometries.modifiers.api.Modifier;
import com.project.paradoxplatformer.utils.geometries.vector.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;



//IDEA THIS SHOULD BE A FACTORY OF MOVES
public class SimpleMovingModifer implements Modifier {

    private static final double CLAMP_VALUE = 1.;
    //initally use protecte, dont know wether it needs on lower classes
    protected double percentage;
    protected long elapseTime;

    public SimpleMovingModifer() {
        this.percentage = 0.;
        this.elapseTime = 0L;
    }

    //deltatime is ambigous, dont know whether it needs to be put as contructor paramter or function neccessity
    //move paramaters are getting numerous
    @Override
    public Pair<Vector2D, Double> moveTo(Vector2D start, Vector2D end, long duration, Interpolator<Vector2D> interpType, long dt) {
        this.elapseTime += dt;
        //doubt, does duration express on nanosec or conventional?, needs a conversion if so
        this.percentage = Math.min(elapseTime / duration, CLAMP_VALUE);
        return Pair.of(interpType.lerp(start, end, percentage), percentage);
    }

    @Override
    public Vector2D step(Vector2D start, Vector2D end, Interpolator<Vector2D> interpType, long dt) {
        System.out.println(1000000.d/dt);
        return interpType.lerp(start, end, 1000000.d/dt);
    }

    //alternatives: 
    //doubts: return type of move should be void or resulting vector and so the field would be 
    //erased, making every move (left, right, jump, spin) affected by move function
    @Override
    public Vector2D stop() {
        return Polar2DVector.nullVector();
    }

    

}
