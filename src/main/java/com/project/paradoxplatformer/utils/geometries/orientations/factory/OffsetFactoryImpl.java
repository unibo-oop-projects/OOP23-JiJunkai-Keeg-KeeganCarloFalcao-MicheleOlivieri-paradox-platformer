package com.project.paradoxplatformer.utils.geometries.orientations.factory;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.orientations.BoxOffset;
import com.project.paradoxplatformer.utils.geometries.orientations.Offset;

import java.util.function.BiFunction;

public class OffsetFactoryImpl implements OffsetFactory {

    private final Dimension anchored;

    public OffsetFactoryImpl(Dimension anchordDim) {
        this.anchored = anchordDim;
    }

    //TEMPLATE METHOD
    private Offset genericOffsetter(BiFunction<Double, Double, Coord2D> poleMapper) {
        return new Offset() {
            private Coord2D combined;

            @Override
            public Offset anchor(Offset offset) {
                var supplyCoord = offset.get();
                this.combined = poleMapper.apply(supplyCoord.x(), supplyCoord.y()); 
                return this;
            }

            @Override
            public Coord2D get() {
                return this.combined;
            }
            
        };
    }

    @Override
    public Offset topLeft() {
        return this.genericOffsetter((x, y) -> new Coord2D(x, y));
    }

    @Override
    public Offset bottomRight() {
        return this.genericOffsetter((x, y) -> new Coord2D(anchored.width() - x, anchored.height() - y));
    }

    @Override
    public Offset bottomLeft() {
        return this.genericOffsetter((x, y) -> new Coord2D(x, anchored.height() - y));
    }

    @Override
    public Offset topRight() {
        return this.genericOffsetter((x, y) -> new Coord2D(anchored.width() - x, y));
    }

    @Override
    public BoxOffset<Dimension> boxOffset() {
        return new BoxOffset<Dimension>() {

            @Override
            public Offset evaluate(Dimension boxDim) {
                return OffsetFactoryImpl.this.genericOffsetter(
                    (x, y) -> new Coord2D(0, y + boxDim.height())
                ).anchor(baseOffset());
            }
            
        };
    }

    private Offset baseOffset() {
        return new Offset() {

            @Override
            public Offset anchor(Offset offset) {
                throw new IllegalStateException("CANT ANCHOR BASE OFFSET");
            }

            @Override
            public Coord2D get() {
                return new Coord2D(0, 0);
            }
            
        };
    }
    
}
