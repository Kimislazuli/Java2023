package edu.hw10.Task2;

public interface FibInterface {
    @Cache(persist = false)
    long fib(long number);
}
