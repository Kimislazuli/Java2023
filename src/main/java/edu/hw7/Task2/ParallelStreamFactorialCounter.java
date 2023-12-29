package edu.hw7.Task2;

import java.util.stream.LongStream;

public final class ParallelStreamFactorialCounter {
    private ParallelStreamFactorialCounter() {
    }

    public static long factorial(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Факториал определён только для неотрицательных чисел.");
        }

        if (value == 0) {
            return 1;
        }

       return LongStream
           .rangeClosed(1, value)
           .parallel()
           .reduce(1, (acc, el) -> acc * el);
    }
}
