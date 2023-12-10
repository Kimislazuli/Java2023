package edu.project4.image;

import edu.project4.fractal_process.domain.FractalImage;
import edu.project4.fractal_process.domain.Pixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {

    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageFormat format) throws IOException {
        Files.deleteIfExists(filename);
        File file = new File(filename.toUri());
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), 1);
        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel != null) {
                    bufferedImage.setRGB(x, y, pixel.getColor().getRGB());
                }
            }

        }
        ImageIO.write(bufferedImage, String.valueOf(format), file);
    }
}
