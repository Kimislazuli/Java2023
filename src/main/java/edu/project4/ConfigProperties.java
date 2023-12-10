package edu.project4;

import edu.project4.image.ImageFormat;
import java.nio.file.Path;

public record ConfigProperties(
    int height,
    int width,
    int samples,
    int iterations,
    int affine,
    String[] nonlinear,
    int symmetry,
    int threads,
    boolean multithreading,
    Path path,
    ImageFormat format) {
}
