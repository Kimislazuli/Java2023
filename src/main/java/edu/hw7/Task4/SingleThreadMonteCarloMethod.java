package edu.hw7.Task4;

import java.security.SecureRandom;

public final class SingleThreadMonteCarloMethod {
    private final static SecureRandom RANDOM = new SecureRandom();

    private SingleThreadMonteCarloMethod() {
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static float approximatePiValue(long iterations) {
        if (iterations <= 0) {
            throw new IllegalArgumentException("Количество итераций должно быть положительным");
        }
        int totalCount = 0;
        int circleCount = 0;
        int radius = 1;

        for (long i = 0; i < iterations; i++) {
            float x = RANDOM.nextFloat(-1, 1);
            float y = RANDOM.nextFloat(-1, 1);
            totalCount++;
            if (x * x + y * y <= radius) {
                circleCount++;
            }
        }
        return (float) circleCount / totalCount * 4;
    }
}
