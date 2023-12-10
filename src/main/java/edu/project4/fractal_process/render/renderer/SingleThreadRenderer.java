package edu.project4.fractal_process.render.renderer;

import edu.project4.fractal_process.domain.AffineTransformation;
import edu.project4.fractal_process.domain.Pixel;
import edu.project4.fractal_process.domain.Point;
import edu.project4.fractal_process.trasnsformations.Transformation;
import java.util.concurrent.ThreadLocalRandom;

public class SingleThreadRenderer extends AbstractRenderer {
    public SingleThreadRenderer(
        int affineTransformationsAmount,
        int xRes, int yRes, Transformation[] transformations
    ) {
        super(affineTransformationsAmount, xRes, yRes, transformations);
    }


    @Override
    public void render(int n, int it, int symmetry) {
        for (int i = 0; i < n; i++) {
            double x = ThreadLocalRandom.current().nextDouble(xMin, xMax);
            double y = ThreadLocalRandom.current().nextDouble(yMin, yMax);

            for (int step = SKIP; step < it; step++) {
                AffineTransformation affineTransformation = randomAffineTransformation();
                Point point = affineTransformation.apply(x, y);

                Transformation transformation = randomTransformation();
                point = transformation.apply(point);

                AffineTransformation postTransformation = randomPostTransformation();
                point = postTransformation.apply(point);
                if (step >= 0) {
                    double angle = Math.PI * 2 / symmetry;
                    for (int j = 0; j < symmetry; j++) {
                        point = point.rotatePoint(angle);

                        x = point.x();
                        y = point.y();

                        if (!pointInImage(x, y)) {
                            continue;
                        }

                        int actualX = (int) (xRes - (xMax - x) / (xMax - xMin) * xRes);
                        int actualY = (int) (yRes - (yMax - y) / (yMax - yMin) * yRes);

                        if (actualX < xRes && actualY < yRes && actualX > 0 && actualY > 0) {
                            Pixel pixel = image.pixel(actualX, actualY);
                            if (pixel == null) {
                                continue;
                            }
                            colorPixel(pixel, affineTransformation);
                        }
                    }
                }
            }
        }
    }

}
