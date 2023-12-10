package edu.project4.fractal_process.trasnsformations;

import edu.project4.fractal_process.domain.Point;
import java.util.function.Function;

public interface Transformation extends Function<Point, Point> {
    Point apply(Point point);
}
