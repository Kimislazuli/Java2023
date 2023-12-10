package edu.project4;

import edu.project4.fractal_process.domain.AffineTransformation;
import edu.project4.fractal_process.domain.Point;
import edu.project4.fractal_process.trasnsformations.HorseshoeTransformation;
import edu.project4.fractal_process.trasnsformations.LinearTransformation;
import edu.project4.fractal_process.trasnsformations.PolarTransformation;
import edu.project4.fractal_process.trasnsformations.SinusoidalTransformation;
import edu.project4.fractal_process.trasnsformations.SphericalTransformation;
import edu.project4.fractal_process.trasnsformations.SwirlTransformation;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import static org.assertj.core.api.Assertions.assertThat;

public class TransformationsTest {
    Point point = new Point(1, 1);

    @Test
    void linear() {
        Point actualResult = new LinearTransformation().apply(point);

        assertThat(actualResult).isEqualTo(point);
    }

    @Test
    void sinusoidal() {
        Point actualResult = new SinusoidalTransformation().apply(point);

        assertThat(actualResult.x()).isCloseTo(0.8, Offset.offset(0.1));
        assertThat(actualResult.y()).isCloseTo(0.5, Offset.offset(0.1));
    }

    @Test
    void spherical() {
        Point actualResult = new SphericalTransformation().apply(point);

        assertThat(actualResult.x()).isCloseTo(0.5, Offset.offset(0.1));
        assertThat(actualResult.y()).isCloseTo(0.5, Offset.offset(0.1));
    }

    @Test
    void polar() {
        Point actualResult = new PolarTransformation().apply(point);

        assertThat(actualResult.x()).isCloseTo(0.25, Offset.offset(0.1));
        assertThat(actualResult.y()).isCloseTo(0.4, Offset.offset(0.1));
    }

    @Test
    void horseshoe() {
        Point actualResult = new HorseshoeTransformation().apply(point);

        assertThat(actualResult.x()).isCloseTo(0, Offset.offset(0.1));
        assertThat(actualResult.y()).isCloseTo(0.7, Offset.offset(0.1));
    }

    @Test
    void swirl() {
        Point actualResult = new SwirlTransformation().apply(point);

        assertThat(actualResult.x()).isCloseTo(1.3, Offset.offset(0.1));
        assertThat(actualResult.y()).isCloseTo(0.4, Offset.offset(0.1));
    }

    @Test
    void affineTransformation() {
        AffineTransformation affineTransformation = new AffineTransformation(1, 0, 0, 0, 1, 0, Color.BLACK);
        Point actualResult = affineTransformation.apply(point);

        assertThat(actualResult).isEqualTo(point);
    }
}
