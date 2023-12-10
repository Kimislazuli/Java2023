package edu.project4.fractal_process.trasnsformations;

import edu.project4.fractal_process.domain.Point;

public class PolarTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));

        return new Point(Math.atan(y / x) / Math.PI, r - 1);
    }
}
