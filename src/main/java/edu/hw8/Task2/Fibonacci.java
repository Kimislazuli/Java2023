package edu.hw8.Task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Fibonacci implements Runnable {
    private static final int MAX_FIBONACCI_ELEMENT = 100;
    private static final Logger LOGGER = LogManager.getLogger(Fibonacci.class);
    private final int n;

    public Fibonacci(int n) {
        if (n < 0 || n > MAX_FIBONACCI_ELEMENT) {
            throw new IllegalArgumentException("Не могу сгенерировать более 100 и менее 0 чисел Фибоначчи.");
        }
        this.n = n;
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        LOGGER.info(Thread.currentThread().threadId() + " thread: start");
        LOGGER.info(Thread.currentThread().threadId()
                + " thread: result "
                + countFibonacci(n)
                + " time: "
                + (System.nanoTime() - start));
    }

    private long countFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return countFibonacci(n - 1) + countFibonacci(n - 2);
    }
}
