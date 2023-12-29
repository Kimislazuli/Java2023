package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Incrementor {
    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet();
    }

    public AtomicInteger getCounter() {
        return counter;
    }
}
