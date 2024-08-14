package com.project.paradoxplatformer.model.entity;

import java.util.Queue;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public abstract class MovingEffect{
    
    protected final Queue<TrajectoryInfo> trasformationStats;
    protected Vector2D displacement;
    protected Vector2D heightVector;
    protected Vector2D widthVector;
    //TODO fro withd and x also
    private final double anchorY;
    private final double anchorHeight;

    protected MovingEffect(final Coord2D position, final Dimension dimension, Queue<TrajectoryInfo> trasfStats) {
        super();
        this.displacement = new Simple2DVector(position.x(), position.y());
        this.anchorY = position.y();
        this.anchorHeight = dimension.height();
        this.heightVector = new Simple2DVector(0.d, dimension.height());
        this.widthVector = new Simple2DVector(dimension.width(), 0.);
        this.trasformationStats = trasfStats;
    }

    // //RREMINEDER ADD ABSTRACT METHODS
    // @Override
    // public void updateState(final long dt) {
    //     if (!this.isIdle && !this.trasformationStats.isEmpty()) {
    //         TrajectoryInfo currentTransf = trasformationStats.peek();
    //         switch (currentTransf.transfType()) {
    //             case DISPLACEMENT:
    //                 this.displacement = this.trasform(this.displacement, currentTransf, dt).getKey();
                    
    //                 break;
    //             case HEIGHT:    
    //                 this.displacement = this.mover.moveTo(
    //                     this.displacement, 
    //                     currentTransf.endpoint().sub(new Simple2DVector(0.d, (anchorHeight - anchorY))),
    //                     currentTransf.duration(),
    //                     interFactory.easeIn(),
    //                     dt
    //                 ).getKey();
    //                 this.heightVector = this.trasform(this.heightVector, currentTransf, dt).getKey();
    //                 break;
    //             case WIDTH:
    //                 this.widthVector = this.trasform(this.widthVector, currentTransf, dt).getKey(); 
    //                 break;
    //             default:
    //                 throw new IllegalStateException();
    //         }

            
    //     }
        
    // }
    
    // private void popWhenFinished(final double percentage) {
        
    //     if(percentage >= 1.d) {
    //         this.mover.stop();
    //         this.trasformationStats.remove();
    //     }
    // }

    // private Pair<Vector2D, Double> trasform(final Vector2D vector2d, final TrajectoryInfo trajectoryInfo, long dt) {
    //     Pair<Vector2D, Double> transf = this.mover.moveTo(
    //                 vector2d, 
    //                 trajectoryInfo.endpoint(),
    //                 trajectoryInfo.duration(),
    //                 interFactory.easeIn(),
    //                 dt
    //             );
    //     this.popWhenFinished(transf.getValue());
    //     return transf;
    // }

}
