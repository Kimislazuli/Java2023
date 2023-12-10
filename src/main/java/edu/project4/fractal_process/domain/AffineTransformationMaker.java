package edu.project4.fractal_process.domain;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Math.pow;

public final class AffineTransformationMaker {
    private static final Color[] COLORS = new Color[] {
        new Color(250, 68, 140),
        new Color(254, 200, 89),
        new Color(67, 181, 160),
        new Color(73, 29, 136)
    };

    private AffineTransformationMaker() {
    }

    public static AffineTransformation createTransformation() {
        double a;
        double b;
        double c;
        double d;
        double e;
        double f;
        do {
            a = ThreadLocalRandom.current().nextDouble(-1, 1);
            b = ThreadLocalRandom.current().nextDouble(-1, 1);
            c = ThreadLocalRandom.current().nextDouble(-1, 1);
            d = ThreadLocalRandom.current().nextDouble(-1, 1);
            e = ThreadLocalRandom.current().nextDouble(-1, 1);
            f = ThreadLocalRandom.current().nextDouble(-1, 1);
        } while (!validCoefficients(a, b, d, e));
        return new AffineTransformation(
            a,
            b,
            c,
            d,
            e,
            f,
            COLORS[ThreadLocalRandom.current().nextInt(0, COLORS.length - 1)]
        );
    }

    private static boolean validCoefficients(double a, double b, double d, double e) {
        return a * a + d * d < 1 && e * e + b * b < 1 && a * a + d * d + e * e + b * b < 1 + pow(a * e - b * d, 2);
    }
}
