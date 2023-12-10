package edu.project4.fractal_process.domain;

import java.awt.Color;

public record AffineTransformation(double a, double b, double c, double d, double e, double f, Color color) {
    public Point apply(double x, double y) {
        return new Point(
            a * x + b * y + c,
            d * x + e * y + f
        );
    }

    public Point apply(Point point) {
        return apply(point.x(), point.y());
    }
}
