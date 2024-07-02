package com.project.paradoxplatformer.utils.geometries.orientations;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public class GraphicOffsetCorrector implements OffsetCorrector{

    private final Vector2D versor;
    private final BoxOffset<Dimension> box;
    private final Offset layout;

    public GraphicOffsetCorrector(Offset layout, BoxOffset<Dimension> box, Vector2D versor) {
        this.layout = layout;
        this.box = box;
        this.versor = versor;
    }

    //GOTTA DO IT ON LAYOUT TOO
    //THEY SHARE SAME PRINCIPLE
    @Override
    public Coord2D correct(Dimension gComponent, Coord2D pos) {
        //MAKE FUNCTIONAL
        var layoutOrigin = this.layout.anchor(this.box.evaluate(gComponent)).get();
        return new Coord2D(
            pos.x() * versor.xComponent() + layoutOrigin.x(),
            pos.y() * versor.yComponent() + layoutOrigin.y() 
        );
    }
    
}
