package edu.hw10.Task2;

import java.io.IOException;

public class Fibonacci implements FibInterface {
    public long fib(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("Число должно быть неотрицательным.");
        }
        if (number == 0 || number == 1) {
            return 1;
        }
        return fib(number - 1) + fib(number - 2);
    }

    public static void main(String[] args) throws IOException {
        Fibonacci fibonacci = new Fibonacci();

        FibInterface fibonacciProxied = CacheProxy.create(fibonacci, FibInterface.class);

        fibonacciProxied.fib(1);
        fibonacciProxied.fib(1);
        fibonacciProxied.fib(10);
        fibonacciProxied.fib(1);
    }
}
