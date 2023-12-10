package edu.project4.fractal_process.domain;

public record Point(double x, double y) {
    public Point rotatePoint(double angle) {
        double rotatedX = x * Math.cos(angle) - y * Math.sin(angle);
        double rotatedY = x * Math.sin(angle) + y * Math.cos(angle);
        return new Point(rotatedX, rotatedY);
    }
}
