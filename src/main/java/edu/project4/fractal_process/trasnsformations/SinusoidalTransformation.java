package edu.project4.fractal_process.trasnsformations;

import edu.project4.fractal_process.domain.Point;

public class SinusoidalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.x()), Math.cos(point.y()));
    }
}
