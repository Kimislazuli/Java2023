package edu.hw7.Task4.multiple_thread_implementation;

import java.util.ArrayList;
import java.util.List;

public final class MultithreadingMonteCarloMethod {
    private static final int THREADS_AMOUNT = 6;

    private MultithreadingMonteCarloMethod() {
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static float approximatePiValue(long iterations) throws InterruptedException {
        if (iterations <= 0) {
            throw new IllegalArgumentException("Количество итераций должно быть положительным");
        }
        long remainingIterations = iterations;
        long iterationsForOneThread = iterations / THREADS_AMOUNT;
        List<MonteCarloMethodThread> threads = new ArrayList<>();

        for (int i = 0; i < THREADS_AMOUNT - 1; i++) {
            MonteCarloMethodThread thread = new MonteCarloMethodThread(iterationsForOneThread);
            thread.start();
            threads.add(thread);
            remainingIterations -= iterationsForOneThread;
        }
        MonteCarloMethodThread thread = new MonteCarloMethodThread(remainingIterations);
        thread.start();
        threads.add(thread);

        long totalCount = 0;
        long circleCount = 0;

        for (int i = 0; i < THREADS_AMOUNT; i++) {
            MonteCarloMethodThread currentThread = threads.get(i);
            currentThread.join();
            totalCount += currentThread.getTotalCount();
            circleCount += currentThread.getCircleCount();
        }

        return (float) circleCount / totalCount * 4;
    }
}
