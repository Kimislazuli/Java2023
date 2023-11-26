package edu.hw7.Task4.multiple_thread_implementation;

import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloMethodThread extends Thread {
    private final static int RADIUS = 1;
    private final long iterations;
    private long totalCount = 0;
    private long circleCount = 0;

    public MonteCarloMethodThread(long iterations) {
        this.iterations = iterations;
    }

    public void run() {
        for (int j = 0; j < iterations; j++) {
            float x = ThreadLocalRandom.current().nextFloat(-1, 1);
            float y = ThreadLocalRandom.current().nextFloat(-1, 1);
            totalCount++;
            if (x * x + y * y <= RADIUS) {
                circleCount++;
            }
        }
    }

    public long getTotalCount() {
        return totalCount;
    }

    public long getCircleCount() {
        return circleCount;
    }
}
