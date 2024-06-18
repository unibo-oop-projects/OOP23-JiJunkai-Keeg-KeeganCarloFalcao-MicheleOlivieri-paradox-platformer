package com.monkeytd.model.coordinates;

public class Polar {

    private double angle;

    public double getAngle() {
        return angle;
    }

    private double magnitude;

    public double getMagnitude() {
        return magnitude;
    }

    public Polar(double r, double theta) {
        this.magnitude = r;
        this.angle = theta;
    }

    public static Polar from(Cartesian cartesian) {
        return cartesian.toPolar();
    }

    public Cartesian toCartesian() {
        return new Cartesian(this.xComponent(), this.yComponent());
    }

    private double yComponent() {
        return Math.round(Math.sin(angle)) * this.magnitude;
    }

    private double xComponent() {
        return Math.round(Math.cos(angle)) * this.magnitude;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(angle);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(magnitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Polar other = (Polar) obj;
        if (Double.doubleToLongBits(angle) != Double.doubleToLongBits(other.angle))
            return false;
        if (Double.doubleToLongBits(magnitude) != Double.doubleToLongBits(other.magnitude))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Polar [angle=" + angle + ", magnitude=" + magnitude + "]";
    }

    public static Polar sum(Polar polar, Polar polar2) {
        Cartesian c = new Cartesian(
            polar.xComponent() + polar2.xComponent(),
            polar.yComponent() + polar2.yComponent()
        );
        return c.toPolar();
    }

    public void setMag(double d) {
        this.magnitude = d;
    }

    public void setAngle(double angle2) {
        this.angle = angle2;
    }
}
