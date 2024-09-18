package com.project.paradoxplatformer.utils.geometries.coordinates.api;

/**
 * Represents a point in Polar coordinates with a magnitude and angle.
 * <p>
 * Provides methods to convert between Polar and Cartesian coordinates,
 * as well as to perform operations such as summing two Polar coordinates.
 * </p>
 */
public final class Polar {

    private double angle;
    private double magnitude;

    /**
     * Constructs a {@code Polar} coordinate with the specified magnitude and angle.
     * 
     * @param r     the magnitude (radius) of the polar coordinate
     * @param theta the angle (theta) in radians of the polar coordinate
     */
    public Polar(final double r, final double theta) {
        this.magnitude = r;
        this.angle = theta;
    }

    /**
     * Returns the angle of this Polar coordinate.
     * 
     * @return the angle in radians
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Returns the magnitude of this Polar coordinate.
     * 
     * @return the magnitude
     */
    public double getMagnitude() {
        return magnitude;
    }

    /**
     * Converts a {@link Cartesian} coordinate to a {@code Polar} coordinate.
     * 
     * @param cartesian the Cartesian coordinate to convert
     * @return the Polar coordinate representation of the Cartesian coordinate
     */
    public static Polar from(final Cartesian cartesian) {
        return cartesian.toPolar();
    }

    /**
     * Converts this Polar coordinate to a {@link Cartesian} coordinate.
     * 
     * @return the Cartesian coordinate representation of this Polar coordinate
     */
    public Cartesian toCartesian() {
        return new Cartesian(this.xComponent(), this.yComponent());
    }

    /**
     * Computes the y-component of this Polar coordinate in Cartesian coordinates.
     * 
     * @return the y-component
     */
    private double yComponent() {
        return Math.round(Math.sin(angle)) * this.magnitude;
    }

    /**
     * Computes the x-component of this Polar coordinate in Cartesian coordinates.
     * 
     * @return the x-component
     */
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
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Polar other = (Polar) obj;
        return Double.doubleToLongBits(angle) == Double.doubleToLongBits(other.angle) &&
                Double.doubleToLongBits(magnitude) == Double.doubleToLongBits(other.magnitude);
    }

    @Override
    public String toString() {
        return "Polar [angle=" + angle + ", magnitude=" + magnitude + "]";
    }

    /**
     * Computes the sum of two Polar coordinates.
     * 
     * @param polar  the first Polar coordinate
     * @param polar2 the second Polar coordinate
     * @return a new Polar coordinate representing the sum of the two input
     *         coordinates
     */
    public static Polar sum(final Polar polar, final Polar polar2) {
        final Cartesian c = new Cartesian(
                polar.xComponent() + polar2.xComponent(),
                polar.yComponent() + polar2.yComponent());
        return c.toPolar();
    }

    /**
     * Sets the magnitude of this Polar coordinate.
     * 
     * @param d the new magnitude
     */
    public void setMag(final double d) {
        this.magnitude = d;
    }

    /**
     * Sets the angle of this Polar coordinate.
     * 
     * @param angle2 the new angle in radians
     */
    public void setAngle(final double angle2) {
        this.angle = angle2;
    }
}
