package com.project.paradoxplatformer.utils.geometries.coordinates.api;

/**
 * Represents a point in Cartesian coordinates with {@code x} and {@code y}
 * values.
 * <p>
 * Provides methods to convert between Cartesian and Polar coordinates, as well
 * as
 * to calculate the magnitude and angle of the point in Polar coordinates.
 * </p>
 */
public final class Cartesian {
    private double y;
    private double x;

    /**
     * Constructs a {@code Cartesian} coordinate with the specified {@code x} and
     * {@code y} values.
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Cartesian(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of this Cartesian coordinate.
     * 
     * @return the x-coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of this Cartesian coordinate.
     * 
     * @param x the new x-coordinate
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Returns the y-coordinate of this Cartesian coordinate.
     * 
     * @return the y-coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of this Cartesian coordinate.
     * 
     * @param y the new y-coordinate
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Converts a {@link Polar} coordinate to a {@code Cartesian} coordinate.
     * 
     * @param polar the polar coordinate to convert
     * @return the Cartesian coordinate
     */
    public static Cartesian from(final Polar polar) {
        return polar.toCartesian();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(x);
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
        final Cartesian other = (Cartesian) obj;
        return Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y)
                && Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x);
    }

    @Override
    public String toString() {
        return "Cartesian Coord [y=" + y + ", x=" + x + "]";
    }

    /**
     * Converts this Cartesian coordinate to a {@link Polar} coordinate.
     * 
     * @return the Polar coordinate representation of this Cartesian coordinate
     */
    public Polar toPolar() {
        return new Polar(this.compMagnitude(), this.computeTheta());
    }

    /**
     * Computes the angle (theta) in radians of this Cartesian coordinate.
     * 
     * @return the angle in radians
     */
    private double computeTheta() {
        if (this.getY() + this.getY() == 0.0d) {
            return 0.0d;
        }
        return Math.atan2(this.getY(), this.getX());
    }

    /**
     * Computes the magnitude of this Cartesian coordinate.
     * 
     * @return the magnitude
     */
    private double compMagnitude() {
        return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2));
    }
}
