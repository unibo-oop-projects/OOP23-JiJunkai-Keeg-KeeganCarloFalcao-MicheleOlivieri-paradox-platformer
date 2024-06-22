package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.world.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.world.vector.api.Vector2D;

import java.util.Optional;

public abstract class AbstractPathObject extends AbstractMutableObject{
    
    protected Optional<TrajectoryInfo> trajStats;
    protected Vector2D displacement;

    protected AbstractPathObject(final Vector2D displacement, Optional<TrajectoryInfo> trajStats) {
        super();
        this.displacement = displacement;
        this.trajStats = trajStats;
    }

        
    @Override
    public void updateState(long dt) {
        if (!this.isIdle) {
            this.displacement = mover.moveTo(this.displacement,
                this.trajStats.map(TrajectoryInfo::endpoint).orElseThrow(IllegalStateException::new),
                this.trajStats.map(TrajectoryInfo::duration).orElseThrow(IllegalStateException::new),
                interFactory.easeIn(),
                dt
            );
        }
    }
    


}
