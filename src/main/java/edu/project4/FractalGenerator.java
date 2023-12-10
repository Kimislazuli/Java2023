package edu.project4;

import edu.project4.fractal_process.render.image_process.GammaCorrectionProcessor;
import edu.project4.fractal_process.render.image_process.ImageProcessor;
import edu.project4.fractal_process.render.renderer.AbstractRenderer;
import edu.project4.fractal_process.render.renderer.MultithreadedRenderer;
import edu.project4.fractal_process.render.renderer.SingleThreadRenderer;
import edu.project4.fractal_process.trasnsformations.HorseshoeTransformation;
import edu.project4.fractal_process.trasnsformations.LinearTransformation;
import edu.project4.fractal_process.trasnsformations.PolarTransformation;
import edu.project4.fractal_process.trasnsformations.SinusoidalTransformation;
import edu.project4.fractal_process.trasnsformations.SphericalTransformation;
import edu.project4.fractal_process.trasnsformations.SwirlTransformation;
import edu.project4.fractal_process.trasnsformations.Transformation;
import edu.project4.image.ImageUtils;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("checkstyle:UncommentedMain")
public final class FractalGenerator {
    private static final Logger LOGGER = LogManager.getLogger(FractalGenerator.class);
    private static final Map<String, Transformation> TRANSFORMATION_MAP;

    static {
        TRANSFORMATION_MAP = new HashMap<>();
        TRANSFORMATION_MAP.put("linear", new LinearTransformation());
        TRANSFORMATION_MAP.put("sinusoidal", new SinusoidalTransformation());
        TRANSFORMATION_MAP.put("swirl", new SwirlTransformation());
        TRANSFORMATION_MAP.put("spherical", new SphericalTransformation());
        TRANSFORMATION_MAP.put("polar", new PolarTransformation());
        TRANSFORMATION_MAP.put("horseshoe", new HorseshoeTransformation());
    }

    private FractalGenerator() {
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ConfigProperties properties =
            PropertyParser.read(Paths.get("src/main/java/edu/project4/config.properties").toString());

        if (properties == null) {
            LOGGER.error("Проблемы при обработке конфигурационного файла.");
        } else {

            Transformation[] transformations = getNonLinearTransformations(properties.nonlinear());

            if (transformations.length == 0) {
                LOGGER.error("Некорректно введены нелинейные преобразования.");
            } else {
                AbstractRenderer renderer;
                if (properties.multithreading()) {
                    renderer = new MultithreadedRenderer(
                        properties.affine(),
                        properties.width(),
                        properties.height(),
                        properties.threads(),
                        transformations
                    );
                } else {
                    renderer = new SingleThreadRenderer(
                        properties.affine(),
                        properties.width(),
                        properties.height(),
                        transformations
                    );
                }
                renderer.render(properties.samples(), properties.iterations(), properties.symmetry());
                ImageProcessor gammaCorrection = new GammaCorrectionProcessor();
                gammaCorrection.process(renderer.getImage());
                ImageUtils.save(
                    renderer.getImage(),
                    properties.path(),
                    properties.format()
                );
            }
        }
    }

    private static Transformation[] getNonLinearTransformations(String[] nonlinear) {
        Transformation[] transformations = new Transformation[nonlinear.length];
        for (int i = 0; i < transformations.length; i++) {
            Transformation transformation = TRANSFORMATION_MAP.get(nonlinear[i]);
            if (transformation != null) {
                transformations[i] = transformation;
            } else {
                return new Transformation[0];
            }
        }
        return transformations;
    }
}
