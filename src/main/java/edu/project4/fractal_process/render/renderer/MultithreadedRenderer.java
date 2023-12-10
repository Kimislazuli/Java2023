package edu.project4.fractal_process.render.renderer;

import edu.project4.fractal_process.domain.AffineTransformation;
import edu.project4.fractal_process.domain.Pixel;
import edu.project4.fractal_process.domain.Point;
import edu.project4.fractal_process.trasnsformations.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class MultithreadedRenderer extends AbstractRenderer {
    private final int threads;
    private int iterationPerSample;
    private int symmetry;

    public MultithreadedRenderer(
        int affineTransformationsAmount,
        int xRes,
        int yRes,
        int threads,
        Transformation[] transformations
    ) {
        super(affineTransformationsAmount, xRes, yRes, transformations);
        this.threads = threads;
    }

    @Override
    public void render(int n, int it, int symmetry) {
        iterationPerSample = it;
        this.symmetry = symmetry;

        int executorsAmount = Math.min(n, threads);

        int iterationsPerThread = n / executorsAmount;
        int remainingIterations = n % executorsAmount;

        List<Runnable> tasks = new ArrayList<>();

        for (int i = 0; i < executorsAmount; i++) {
            int samplesPerThread = iterationsPerThread + (remainingIterations > 0 ? 1 : 0);
            remainingIterations--;

            tasks.add(() -> task(samplesPerThread));
        }

        try (ExecutorService executorService = Executors.newFixedThreadPool(executorsAmount)) {
            for (Runnable task : tasks) {
                executorService.execute(task);
            }
        }
    }

    public void task(int samples) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < samples; i++) {
            double x = random.nextDouble(xMin, xMax);
            double y = random.nextDouble(yMin, yMax);

            for (int step = SKIP; step < iterationPerSample; step++) {
                AffineTransformation affineTransformation = randomAffineTransformation(random);
                Point point = affineTransformation.apply(x, y);

                Transformation transformation = randomTransformation(random);
                point = transformation.apply(point);

                AffineTransformation postTransformation = randomPostTransformation(random);
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

                        if (actualX < xRes && actualY < yRes && actualX >= 0 && actualY >= 0) {
                            Pixel pixel = image.pixel(actualX, actualY);
                            synchronized (Objects.requireNonNull(pixel)) {
                                colorPixel(pixel, affineTransformation);
                            }
                        }
                    }
                }
            }
        }
    }
}
