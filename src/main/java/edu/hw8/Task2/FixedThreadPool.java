package edu.hw8.Task2;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

public final class FixedThreadPool implements ThreadPool {
    private final Thread[] threadPool;
    private final BlockingQueue<Runnable> taskQueue;

    private FixedThreadPool(int amountOfThreads) {
        taskQueue = new LinkedBlockingQueue<>();
        threadPool = Stream.generate(() -> new Thread(
                        () -> {
                            while (!Thread.currentThread().isInterrupted()) {
                                try {
                                    Runnable task = taskQueue.take();
                                    task.run();
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                        }
                    )
            ).limit(amountOfThreads)
            .toArray(Thread[]::new);
    }

    public static FixedThreadPool create(int amountOfThreads) {
        return new FixedThreadPool(amountOfThreads);
    }

    @Override
    public void start() {
        Arrays.stream(threadPool).forEach(Thread::start);
    }

    @Override
    public void execute(Runnable runnable) throws InterruptedException {
        if (runnable == null) {
            throw new NullPointerException();
        }
        taskQueue.put(runnable);
    }

    @Override
    public void close() {
        Arrays.stream(threadPool).forEach(Thread::interrupt);
    }
}
