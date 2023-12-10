package edu.project4.fractal_process.render.renderer;

import edu.project4.fractal_process.domain.AffineTransformation;
import edu.project4.fractal_process.domain.AffineTransformationMaker;
import edu.project4.fractal_process.domain.FractalImage;
import edu.project4.fractal_process.domain.Pixel;
import edu.project4.fractal_process.trasnsformations.Transformation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractRenderer {
    protected final double xMin;
    protected final double xMax;
    protected final double yMin;
    protected final double yMax;
    protected static final int SKIP = -20;
    protected final List<AffineTransformation> affineTransformations;
    protected final List<AffineTransformation> postTransformations;
    protected final Transformation[] transformations;
    protected final FractalImage image;
    protected final int xRes;
    protected final int yRes;

    public AbstractRenderer(
        int affineTransformationsAmount,
        int xRes, int yRes, Transformation... transformations
    ) {
        image = FractalImage.create(xRes, yRes);
        this.xRes = xRes;
        this.yRes = yRes;

        if (xRes > yRes) {
            yMax = 1;
            xMax = (double) xRes / yRes;
        } else if (xRes < yRes) {
            xMax = 1;
            yMax = (double) yRes / xRes;
        } else {
            xMax = 1;
            yMax = 1;
        }

        yMin = -yMax;
        xMin = -xMax;

        affineTransformations = new ArrayList<>();
        postTransformations = new ArrayList<>();
        for (int i = 0; i < affineTransformationsAmount; i++) {
            affineTransformations.add(AffineTransformationMaker.createTransformation());
            postTransformations.add(AffineTransformationMaker.createTransformation());
        }

        this.transformations = transformations;
    }

    public FractalImage getImage() {
        return image;
    }

    public abstract void render(int n, int it, int symmetry) throws InterruptedException, ExecutionException;

    protected boolean pointInImage(double x, double y) {
        return x < xMax && xMin <= x && y < yMax && y >= yMin;
    }

    protected AffineTransformation randomAffineTransformation() {
        return randomListElement(affineTransformations);
    }

    protected AffineTransformation randomAffineTransformation(Random random) {
        return randomListElement(affineTransformations, random);
    }

    protected AffineTransformation randomPostTransformation() {
        return randomListElement(postTransformations);
    }

    protected AffineTransformation randomPostTransformation(Random random) {
        return randomListElement(postTransformations, random);
    }

    private <T> T randomListElement(List<T> list) {
        return list.get(ThreadLocalRandom.current().nextInt(0, affineTransformations.size()));
    }

    private <T> T randomListElement(List<T> list, Random random) {
        return list.get(random.nextInt(0, affineTransformations.size()));
    }

    protected Transformation randomTransformation() {
        if (transformations.length == 1) {
            return transformations[0];
        } else {
            return transformations[ThreadLocalRandom.current().nextInt(0, transformations.length - 1)];
        }
    }

    protected Transformation randomTransformation(Random random) {
        if (transformations.length == 1) {
            return transformations[0];
        } else {
            return transformations[random.nextInt(0, transformations.length - 1)];
        }
    }

    protected synchronized void colorPixel(Pixel pixel, AffineTransformation affineTransformation) {
        if (pixel.getHitCount() == 0) {
            pixel.newHit();
            pixel.setColor(affineTransformation.color());
        } else {
            pixel.newHit();
            pixel.setColor(
                new Color(
                    (pixel.getColor().getRed() + affineTransformation.color().getRed()) / 2,
                    (pixel.getColor().getGreen() + affineTransformation.color().getGreen()) / 2,
                    (pixel.getColor().getBlue() + affineTransformation.color().getBlue()) / 2
                )
            );
        }
    }
}
