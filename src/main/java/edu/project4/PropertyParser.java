package edu.project4;

import edu.project4.image.ImageFormat;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SuppressWarnings("checkstyle:MultipleStringLiterals")
public final class PropertyParser {
    private PropertyParser() {
    }

    private static final Map<String, ImageFormat> FORMATS;

    static {
        FORMATS = new HashMap<>();
        FORMATS.put("jpeg", ImageFormat.JPEG);
        FORMATS.put("bmp", ImageFormat.BMP);
        FORMATS.put("png", ImageFormat.PNG);
    }

    public static ConfigProperties read(String filePath) {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);

            int height = Integer.parseInt(properties.getProperty("height", "1000"));
            int width = Integer.parseInt(properties.getProperty("width", "1000"));
            int samples = Integer.parseInt(properties.getProperty("samples", "10"));
            int iterations = Integer.parseInt(properties.getProperty("iterations", "10000000"));
            int affine = Integer.parseInt(properties.getProperty("affine", "0"));
            String[] nonlinear = readArray(properties.getProperty("nonlinear"));
            int symmetry = Integer.parseInt(properties.getProperty("symmetry", "0"));
            int threads = Integer.parseInt(properties.getProperty("threads", "0"));
            boolean multithreading = Boolean.parseBoolean(properties.getProperty("multithreading", "false"));
            ImageFormat format = FORMATS.get(properties.getProperty("format", "png"));
            Path path = Paths.get(properties.getProperty("path"));

            ConfigProperties readyProperties = new ConfigProperties(
                height,
                width,
                samples,
                iterations,
                affine,
                nonlinear,
                symmetry,
                threads,
                multithreading,
                path,
                format
            );

            if (propertiesIsValid(readyProperties)) {
                return readyProperties;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    private static boolean propertiesIsValid(ConfigProperties properties) {
        return properties.height() > 0 && properties.width() > 0
            && properties.iterations() > 0 && properties.samples() > 0
            && properties.affine() > 0 && properties.symmetry() > 0
            && properties.threads() > 0 && properties.format() != null;
    }

    private static String[] readArray(String valuesAsString) {
        return Arrays.asList(valuesAsString.split(","))
            .stream()
            .map(String::trim)
            .toArray(String[]::new);
    }
}
