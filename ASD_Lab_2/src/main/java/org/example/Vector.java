package org.example;

public class Vector {
    private double r;
    private double theta;

    public Vector(double r, double theta) {
        this.r = r;
        this.theta = theta;
    }

    public double getX() {
        return r * Math.cos(theta);
    }

    public double getY() {
        return r * Math.sin(theta);
    }

    @Override
    public String toString() {
        return String.format("r=%.2f, θ=%.2f, X=%.2f, Y=%.2f",
                r, theta, getX(), getY());
    }
}
