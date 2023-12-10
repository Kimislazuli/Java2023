package edu.project4.fractal_process.trasnsformations;

import edu.project4.fractal_process.domain.Point;

public class SwirlTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double r2 = Math.pow(point.x(), 2) + Math.pow(point.y(), 2);
        return new Point(
            point.x() * Math.sin(r2) - point.y() * Math.cos(r2),
            point.x() * Math.cos(r2) + point.y() * Math.sin(r2)
        );
    }
}
