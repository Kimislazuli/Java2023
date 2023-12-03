package edu.hw8.Task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MultithreadingFibonacciDemonstrationTest {
    public static void main(String[] args) throws InterruptedException {

        try (FixedThreadPool fixedThreadPool = FixedThreadPool.create(3)) {
//        FixedThreadPool fixedThreadPool = FixedThreadPool.create(3);
//        try (ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3)) {
            fixedThreadPool.start();
            fixedThreadPool.execute(new Fibonacci(0));
            fixedThreadPool.execute(new Fibonacci(10));
            fixedThreadPool.execute(new Fibonacci(20));
            fixedThreadPool.execute(new Fibonacci(30));
            fixedThreadPool.execute(new Fibonacci(40));
            fixedThreadPool.execute(new Fibonacci(50));
            Thread.sleep(10000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
