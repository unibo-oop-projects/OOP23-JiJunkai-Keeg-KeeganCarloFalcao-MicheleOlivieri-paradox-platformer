package com.project.paradoxplatformer.utils.geometries;

public record Vector(double x, double y) {

    public Vector sum(Vector vector) {
        return new Vector(this.x+ vector.x, this.y+ vector.y);
    }

    public double module() {
        return (double) Math.sqrt(this.x*this.x+this.y*this.y);
    }

    public Vector mul(double factor){
        return new Vector(this.x*factor, this.y*factor);
    }
}
