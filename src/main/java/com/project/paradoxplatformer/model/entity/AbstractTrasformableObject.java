package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.world.Dimension;
import com.project.paradoxplatformer.utils.world.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.world.vector.Simple2DVector;
import com.project.paradoxplatformer.utils.world.vector.api.Vector2D;

import java.util.Queue;

import org.apache.commons.lang3.tuple.Pair;

public abstract class AbstractTrasformableObject extends AbstractMutableObject{
    
    protected Queue<TrajectoryInfo> trasformationStats;
    protected Vector2D displacement;
    protected Vector2D heightVector;
    protected Vector2D widthVector;

    protected AbstractTrasformableObject(final Coord2D position, final Dimension dimension, Queue<TrajectoryInfo> trasfStats) {
        super();
        this.displacement = new Simple2DVector(position.x(), position.y());
        this.heightVector = new Simple2DVector(0.d, dimension.height());
        this.heightVector = new Simple2DVector(dimension.width(), 0.);
        this.trasformationStats = trasfStats;
    }

    //RREMINEDER ADD ABSTRACT METHODS
    @Override
    public void updateState(long dt) {
        if (!this.isIdle) {
            TrajectoryInfo currentTransf = trasformationStats.peek();
            Pair<Vector2D, Double> trasformer = mover.moveTo(this.displacement,currentTransf.endpoint(), currentTransf.duration(), interFactory.easeIn(), dt);
                if(trasformer.getValue() >= 1.d) {
                    trasformationStats.remove();
                }
            switch (currentTransf.transfType()) {
                case DISPLACEMENT:
                    this.displacement = trasformer.getKey();
                    break;
                case HEIGHT:
                    this.heightVector = trasformer.getKey();
                    break;
                case WIDTH:
                    this.widthVector = trasformer.getKey();
                    break;
                default:
                    throw new IllegalStateException();
            }
        }
        
    }
    


}
