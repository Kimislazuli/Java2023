package edu.project4.fractal_process.trasnsformations;

import edu.project4.fractal_process.domain.Point;

public class LinearTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(point.x(), point.y());
    }
}
