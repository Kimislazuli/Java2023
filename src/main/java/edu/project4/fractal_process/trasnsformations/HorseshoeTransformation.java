package edu.project4.fractal_process.trasnsformations;

import edu.project4.fractal_process.domain.Point;

public class HorseshoeTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        return new Point(1 / r * ((point.x() - point.y()) * (point.x() + point.y())), 1 / r * point.x() * point.y());
    }
}
