package edu.project4.fractal_process.render.image_process;

import edu.project4.fractal_process.domain.FractalImage;

@FunctionalInterface
public
interface ImageProcessor {
    void process(FractalImage image);
}
