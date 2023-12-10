package edu.project4.fractal_process.render.image_process;

import edu.project4.fractal_process.domain.FractalImage;
import edu.project4.fractal_process.domain.Pixel;
import java.awt.Color;

public class GammaCorrectionProcessor implements ImageProcessor {
    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public void process(FractalImage image) {
        double max = 0.0;
        double gamma = 0.5;
        for (int row = 0; row < image.width(); row++) {
            for (int col = 0; col < image.height(); col++) {
                Pixel pixel = image.pixel(row, col);
                if (pixel.getHitCount() != 0) {
                    pixel.setNormal(Math.log10(pixel.getHitCount()));
                    if (pixel.getNormal() > max) {
                        max = pixel.getNormal();
                    }
                }
            }
        }
        for (int row = 0; row < image.width(); row++) {
            for (int col = 0; col < image.height(); col++) {
                Pixel pixel = image.pixel(row, col);
                pixel.setNormal(pixel.getNormal() / max);
                pixel.setColor(new Color(
                    (int) (pixel.getColor().getRed() * Math.pow(pixel.getNormal(), (1f / gamma))),
                    (int) (pixel.getColor().getGreen() * Math.pow(pixel.getNormal(), (1f / gamma))),
                    (int) (pixel.getColor().getBlue() * Math.pow(pixel.getNormal(), (1f / gamma)))
                ));
            }
        }
    }
}
