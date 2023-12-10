package edu.project4.fractal_process.domain;

public record FractalImage(Pixel[][] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        Pixel[][] pixels = new Pixel[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i][j] = new Pixel();
            }
        }
        return new FractalImage(pixels, width, height);
    }

    public Pixel pixel(int x, int y) {
        if (y >= height || x >= width) {
            return null;
        }
        return data[x][y];
    }
}
