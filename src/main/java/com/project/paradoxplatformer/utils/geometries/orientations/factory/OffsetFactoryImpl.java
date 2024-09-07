package com.project.paradoxplatformer.utils.geometries.orientations.factory;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.orientations.BoxOffset;
import com.project.paradoxplatformer.utils.geometries.orientations.Offset;
import java.util.function.BiFunction;

public final class OffsetFactoryImpl implements OffsetFactory {

    private final Dimension anchored;

    public OffsetFactoryImpl(final Dimension anchordDim) {
        this.anchored = anchordDim;
    }

    @Override
    public Offset topLeft() {
        return new TemplateOffset((x, y) -> new Coord2D(x, y));
    }

    @Override
    public Offset bottomRight() {
        return new TemplateOffset((x, y) -> new Coord2D(anchored.width() - x, anchored.height() - y));
    }

    @Override
    public Offset bottomLeft() {
        return new TemplateOffset((x, y) -> new Coord2D(x, anchored.height() - y));
    }

    @Override
    public Offset topRight() {
        return new TemplateOffset((x, y) -> new Coord2D(anchored.width() - x, y));
    }

    @Override
    public BoxOffset<Dimension> boxOffset() {
        return boxDim -> OffsetFactoryImpl.this.new TemplateOffset(
                (x, y) -> new Coord2D(0, y + boxDim.height())
            ).anchor(new BaseOffset());
    }

    //TEMPLATE METHOD
    private final class TemplateOffset implements Offset {
        private Coord2D combined;
        private final BiFunction<Double, Double, Coord2D> poleMapper;

        public TemplateOffset(final BiFunction<Double, Double, Coord2D> poleMapper) {
            this.poleMapper = poleMapper;
        }

        @Override
        public Offset anchor(Offset offset) {
            final var supplyCoord = offset.get();
            this.combined = poleMapper.apply(supplyCoord.x(), supplyCoord.y()); 
            return this;
        }

        @Override
        public Coord2D get() {
            return this.combined;
        }
    }
    
}
